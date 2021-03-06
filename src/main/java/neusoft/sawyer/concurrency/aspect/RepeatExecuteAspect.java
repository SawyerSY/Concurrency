package neusoft.sawyer.concurrency.aspect;

import lombok.extern.slf4j.Slf4j;
import neusoft.sawyer.concurrency.annotation.RepeatExecute;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by sawyer on 2018/6/11.
 * <p>
 * 注意：
 * <p>
 * 1. 当有异常抛出时，会抓异常并打印ERROR日志，不会block执行.
 * <p>
 * 2. 默认最大并发数200，总线程数5000.
 * <p>
 * 使用示例:
 * <pre>
 * {@link RepeatExecute}(clientTotal = 100, threadTotal = 1000)}
 * void execute() {
 *      // do repeat method.
 * }
 * </pre>
 */
@Slf4j
@Aspect
@Component
public class RepeatExecuteAspect {

    @Pointcut("@annotation(neusoft.sawyer.concurrency.annotation.RepeatExecute)")
    private void batchExecutePointCut() {

    }

    @Around("batchExecutePointCut()")
    private Object advice(ProceedingJoinPoint pjp) throws Throwable {

        MethodSignature signature = (MethodSignature) pjp.getSignature();
        RepeatExecute annotation = signature.getMethod().getAnnotation(RepeatExecute.class);
        int clientTotal = annotation.clientTotal();
        int threadTotal = annotation.threadTotal();

        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(threadTotal);
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        List<Callable<Object>> callableList = new ArrayList<>();

        final Object[] result = {null};

        for (int i = 0; i < clientTotal; i++) {
            Callable<Object> callable = () -> {
                try {
                    semaphore.acquire();
                    result[0] = pjp.proceed(pjp.getArgs());
                    return result[0];
                } catch (Throwable throwable) {
                    log.error("Invoke exception.", throwable);
                    throw new RuntimeException(throwable);
                } finally {
                    semaphore.release();
                    countDownLatch.countDown();
                }
            };
            callableList.add(callable);
        }


        executorService.invokeAll(callableList);

        countDownLatch.await();
        executorService.shutdown();

        return result[0];
    }
}
