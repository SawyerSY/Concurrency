package neusoft.sawyer.concurrency.example.test;

import neusoft.sawyer.concurrency.example.publish.Escape;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by sawyer on 2018/6/11.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MyTest {

    @Test
    public void main() throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(5000);
        executorService.execute(() -> {
            for (int i = 0; i < 5000; i++) {
                new Escape();
                countDownLatch.countDown();
            }
        });
        countDownLatch.await();
        executorService.shutdown();
    }
}
