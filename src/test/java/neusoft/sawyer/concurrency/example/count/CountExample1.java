package neusoft.sawyer.concurrency.example.count;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by sawyer on 2018/6/8.
 */
@Slf4j
@RunWith(SpringRunner.class)
@NotThreadSafe
@SpringBootTest
public class CountExample1 {

    private static int clientTotal = 5000;  // 请求线程总数
    private static int threadTotal = 200;    // 线程并发数
    private static int count = 0;   // 计数器

    private static final Logger LOGGER = LoggerFactory.getLogger(CountExample1.class);

    @Test
    public void test() throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    LOGGER.error("exception", e);
                }
                CountExample1.add();
                semaphore.release();
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        LOGGER.info("count: {}", count);
    }

    private static void add() {
        count++;
    }
}
