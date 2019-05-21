package neusoft.sawyer.concurrency.example.lock;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by sawyer on 2019-05-20.
 */
@Slf4j
@Component
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class LockExample {

    @Test
    public void testReentrantLock() throws InterruptedException {
        long start = System.currentTimeMillis();
        this.reentrantLockTest.invoke();
        long end = System.currentTimeMillis();
        System.out.println(String.format("Count = %s, Cost = %s", ReentrantLockTest.count, (end - start)));
    }

    @Test
    public void testReentrantReadWriteLock() {
        long start = System.currentTimeMillis();
        this.reentrantReadWriteLockTest.invoke();
        long end = System.currentTimeMillis();
        System.out.println(String.format("Cost = %s", (end - start)));
    }

    @Resource
    private ReentrantLockTest reentrantLockTest;
    @Resource
    private ReentrantReadWriteLockTest reentrantReadWriteLockTest;
}
