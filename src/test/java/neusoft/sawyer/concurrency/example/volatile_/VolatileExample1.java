package neusoft.sawyer.concurrency.example.volatile_;

import lombok.extern.slf4j.Slf4j;
import neusoft.sawyer.concurrency.annotation.NotThreadSafe;
import neusoft.sawyer.concurrency.annotation.RepeatExecute;
import neusoft.sawyer.concurrency.util.ApplicationContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by sawyer on 2018/6/13.
 */
@Slf4j
@Component
@NotThreadSafe
public class VolatileExample1 {

    private static final Logger LOGGER = LoggerFactory.getLogger(VolatileExample1.class);

    private static volatile int count = 0;

    public void invoke() {
        VolatileExample1 thisClass = new ApplicationContextHolder().getApplicationContext().getBean(VolatileExample1.class);
        thisClass.add();
        LOGGER.info("count={}", count);
    }

    @RepeatExecute
    public void add() {
        count++;
    }
}
