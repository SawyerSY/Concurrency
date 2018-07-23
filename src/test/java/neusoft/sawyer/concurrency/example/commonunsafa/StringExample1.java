package neusoft.sawyer.concurrency.example.commonunsafa;

import lombok.extern.slf4j.Slf4j;
import neusoft.sawyer.concurrency.annotation.NotThreadSafe;
import neusoft.sawyer.concurrency.annotation.RepeatExecute;
import org.springframework.stereotype.Component;

/**
 * Created by sawyer on 2018/7/17.
 */
@Slf4j
@Component
@NotThreadSafe
public class StringExample1 {

    static StringBuilder stringBuilder = new StringBuilder();

    static StringBuffer stringBuffer = new StringBuffer();

    @RepeatExecute
    void updateString() {
        stringBuilder.append("1");
        stringBuffer.append("1");
    }
}
