package neusoft.sawyer.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by sawyer on 2018/7/24.
 */
@Slf4j
public class CountDownLatchExample1 {

    private static final int THREAD_COUNT = 200;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        final CountDownLatch countDownLatch = new CountDownLatch(THREAD_COUNT);

        for (int i = 0; i < THREAD_COUNT; i++) {
            int threadNumber = i;
            executorService.execute(() -> {
                try {
                    invoke(threadNumber);
                } catch (InterruptedException e) {
                    log.error("exception", e);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }

        countDownLatch.await(50, TimeUnit.MILLISECONDS);

        log.info("finished");

        executorService.shutdown();
    }

    private static void invoke(int threadNumber) throws InterruptedException {
        Thread.sleep(100);
        log.info("Thread number = {}", threadNumber);
    }
}
