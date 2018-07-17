package neusoft.sawyer.concurrency.example.publish;

import lombok.extern.slf4j.Slf4j;
import neusoft.sawyer.concurrency.annotation.NotRecommend;
import neusoft.sawyer.concurrency.annotation.NotThreadSafe;
import neusoft.sawyer.concurrency.annotation.RepeatExecute;
import org.springframework.stereotype.Component;

/**
 * Created by sawyer on 2018/6/15.
 */
@Slf4j
@Component
@NotRecommend
@NotThreadSafe
public class Escape {

    private Integer thisCanBeEscape = 0;

    public Escape() {
        new InnerClass();
    }

    public class InnerClass {
        private InnerClass() {
            this.log();
        }

        private void log() {
            log.info("{}", Escape.this.thisCanBeEscape);
        }
    }

    @RepeatExecute
    public void invoke() {
        new Escape();
    }
}
