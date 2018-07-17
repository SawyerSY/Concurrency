package neusoft.sawyer.concurrency.example.atomic;

import lombok.extern.slf4j.Slf4j;
import neusoft.sawyer.concurrency.annotation.ThreadSafe;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by sawyer on 2018/6/9.
 */
@Slf4j
@ThreadSafe
@SpringBootTest
@RunWith(SpringRunner.class)
public class AtomicBooleanExample {

    private static int clientTotal = 5000;

    private static int threadTotal = 200;

    private static AtomicBoolean isHappened = new AtomicBoolean(false);

    @Test
    public void invoke() throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(threadTotal);
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    log.error("Exception", e);
                }
                test();
                semaphore.release();
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("isHappened:{}", isHappened);
    }

    private static void test() {
        if (isHappened.compareAndSet(false, true)) {
            log.info("execute");
        }
    }
}
