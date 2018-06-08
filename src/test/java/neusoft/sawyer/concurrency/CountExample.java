package neusoft.sawyer.concurrency;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by sawyer on 2018/6/5.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CountExample {

    private static final int THREAD_NUM = 200;
    private static final int CLIENT_NUM = 500;

    @Test
    public void invoke() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(5);

    }
}
