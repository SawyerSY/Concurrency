package neusoft.sawyer.concurrency.aspect;

import lombok.extern.slf4j.Slf4j;
import neusoft.sawyer.concurrency.annotation.RepeatExecute;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by sawyer on 2018/6/11.
 */
@Slf4j
@Aspect
@Component
public class RepeatExecuteAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(RepeatExecuteAspect.class);

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
                    semaphore.release();
                    countDownLatch.countDown();
                    return result[0];
                } catch (Throwable throwable) {
                    LOGGER.error("Exception", throwable);
                    throw new RuntimeException(throwable);
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