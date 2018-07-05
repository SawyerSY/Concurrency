package neusoft.sawyer.concurrency.example.atomic;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import neusoft.sawyer.concurrency.annotation.ThreadSafe;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * Created by sawyer on 2018/6/9.
 */
@Slf4j
@ThreadSafe
@SpringBootTest
@RunWith(SpringRunner.class)
public class AtomicReferenceFieldUpdateExample {

    private static AtomicIntegerFieldUpdater<AtomicReferenceFieldUpdateExample> UPDATER = AtomicIntegerFieldUpdater.newUpdater(AtomicReferenceFieldUpdateExample.class, "count");

    @Getter
    private volatile int count = 100;   // volatile 修饰，不能是 static

    private static final Logger LOGGER = LoggerFactory.getLogger(AtomicReferenceExample.class);

    @Test
    public void test() {

        AtomicReferenceFieldUpdateExample example = new AtomicReferenceFieldUpdateExample();

        if (UPDATER.compareAndSet(example, 100, 120)) {
            LOGGER.info("update success 1: {}", example.count);
        }

        if (UPDATER.compareAndSet(example, 100, 120)) {
            LOGGER.info("update success 2: {}", example.count);
        } else {
            LOGGER.info("update field 2: {}", example.count);
        }
    }
}
