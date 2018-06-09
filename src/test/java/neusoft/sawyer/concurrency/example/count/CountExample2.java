package neusoft.sawyer.concurrency.example.count;

import lombok.extern.slf4j.Slf4j;
import neusoft.sawyer.concurrency.annoation.ThreadSafe;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by sawyer on 2018/6/9.
 */
@Slf4j
@ThreadSafe
@SpringBootTest
@RunWith(SpringRunner.class)
public class CountExample2 {

    private static int clientTotal = 5000;

    private static int threadTotal = 200;

    private static AtomicInteger count = new AtomicInteger(0);

    private static final Logger LOGGER = LoggerFactory.getLogger(CountExample1.class);

    @Test
    public void test() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(threadTotal);
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    LOGGER.warn("Exception", e);
                }
                add();
                semaphore.release();
                countDownLatch.countDown();
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            LOGGER.warn("Exception", e);
        }
        executorService.shutdown();
        LOGGER.info("count = {}", count);
    }

    private static void add() {
        count.incrementAndGet();
    }
}
