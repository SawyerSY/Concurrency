package neusoft.sawyer.concurrency.example.atomic;

import lombok.extern.log4j.Log4j;
import neusoft.sawyer.concurrency.annotation.ThreadSafe;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by sawyer on 2018/6/9.
 */
@Log4j
@ThreadSafe
@SpringBootTest
@RunWith(SpringRunner.class)
public class AtomicReferenceExample {

    private static final AtomicReference<Integer> count = new AtomicReference<>(0);

    private static final Logger LOGGER = LoggerFactory.getLogger(AtomicReferenceExample.class);

    @Test
    public void test() {
        count.compareAndSet(0, 2);  // count = 2
        count.compareAndSet(0, 1);  //
        count.compareAndSet(1, 3);  //
        count.compareAndSet(2, 4);  // count = 4
        count.compareAndSet(3, 5);  //
        LOGGER.info("count: {}", count.get());
    }
}
