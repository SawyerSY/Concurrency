package neusoft.sawyer.concurrency.example.lock;

import lombok.extern.slf4j.Slf4j;
import neusoft.sawyer.concurrency.annotation.RepeatExecute;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by sawyer on 2019-05-20.
 */
@Slf4j
@Component
public class ReentrantLockTest {

    private static final Lock LOCK = new ReentrantLock(true);

    static int count = 0;

    @RepeatExecute(clientTotal = 100000, threadTotal = 1000)
    void invoke() throws InterruptedException {
        LOCK.tryLock(100, TimeUnit.MILLISECONDS);
        try {
            count++;
        } finally {
            LOCK.unlock();
        }
    }
}
