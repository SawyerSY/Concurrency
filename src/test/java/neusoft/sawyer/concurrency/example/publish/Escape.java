package neusoft.sawyer.concurrency.example.publish;

import lombok.extern.slf4j.Slf4j;
import neusoft.sawyer.concurrency.annotation.NotRecommend;
import neusoft.sawyer.concurrency.annotation.NotThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by sawyer on 2018/6/15.
 */
@Slf4j
@Component
@NotRecommend
@NotThreadSafe
public class Escape {

    private static final Logger LOGGER = LoggerFactory.getLogger(Escape.class);

    private int thisCanBeEscape = 0;

    public Escape() {
        new InnerClass();
    }

    private class InnerClass {
        public InnerClass() {
            this.log();
        }

        private void log() {
            LOGGER.info("{}", Escape.this.thisCanBeEscape);
        }
    }
}
