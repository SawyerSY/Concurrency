package neusoft.sawyer.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;
import neusoft.sawyer.concurrency.annotation.RepeatExecute;
import neusoft.sawyer.concurrency.annotation.ThreadSafe;
import neusoft.sawyer.concurrency.util.ApplicationContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by sawyer on 2018/6/11.
 */
@Slf4j
@Component
@ThreadSafe
public class SynchronizedExample2 {

    private static final Logger LOGGER = LoggerFactory.getLogger(SynchronizedExample2.class);

    private static int count = 0;

    public void invoke() {
        SynchronizedExample2 thisClass = new ApplicationContextHolder().getApplicationContext().getBean(SynchronizedExample2.class);
        thisClass.add();
        LOGGER.info("count = {}", count);
    }

    @RepeatExecute()
    public synchronized void add() {
        count++;
    }
}
