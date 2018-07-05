package neusoft.sawyer.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by sawyer on 2018/6/11.
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class SynchronizedExample1 {

    private static final Logger LOGGER = LoggerFactory.getLogger(SynchronizedExample1.class);

    // 修饰一个代码块
    private void test1() {
        synchronized (this) {
            invokeLog();
        }
    }

    // 修饰一个方法
    private synchronized void test2() {
        invokeLog();
    }

    // 修饰一个静态方法
    private static synchronized void test3() {
        invokeLog();
    }

    // 修饰一个类
    private void test4() {
        synchronized (SynchronizedExample1.class) {
            invokeLog();
        }
    }

    private static void invokeLog() {
        for (int i = 0; i < 10; i++) {
            LOGGER.info("i = {}", i);
        }
    }

    @Test
    public void main() {
        SynchronizedExample1 synchronizedExample1 = new SynchronizedExample1();
        SynchronizedExample1 synchronizedExample2 = new SynchronizedExample1();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(SynchronizedExample1::test3);
        executorService.execute(synchronizedExample2::test4);
    }
}
