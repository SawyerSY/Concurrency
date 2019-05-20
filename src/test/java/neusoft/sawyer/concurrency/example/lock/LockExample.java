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
    public void main() throws InterruptedException {
        long start = System.currentTimeMillis();
        this.reentrantLock.invoke();
        long end = System.currentTimeMillis();
        System.out.println(String.format("Count = %s, Cost = %s", ReentrantLock.count, (end - start)));
    }

    @Resource
    private ReentrantLock reentrantLock;
}
