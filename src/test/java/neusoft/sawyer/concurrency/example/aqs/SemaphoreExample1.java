package neusoft.sawyer.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by sawyer on 2018/7/24.
 */
@Slf4j
public class SemaphoreExample1 {


    private static final int THREAD_COUNT = 20;
    private static final int THREAD_TOTAL = 5;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        Semaphore semaphore = new Semaphore(THREAD_TOTAL);

        for (int i = 0; i < THREAD_COUNT; i++) {
            final int threadNumber = i;
            executorService.execute(() -> {
                try {
                    if (semaphore.tryAcquire(20)) {
                        invoke(threadNumber);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release(20);
                }
            });
        }

        log.info("finished");

        executorService.shutdown();
    }

    private static void invoke(int threadNumber) throws InterruptedException {
        Thread.sleep(1000);
        log.info("Thread number = {}", threadNumber);
    }
}
