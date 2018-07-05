package neusoft.sawyer.concurrency.example;

import neusoft.sawyer.concurrency.annotation.RepeatExecute;
import org.springframework.stereotype.Component;

/**
 * Created by sawyer on 2018/6/11.
 */
@Component
public class MyExample {

    @RepeatExecute
    public String test(String p1, int p2) {
        System.out.println(1 / 0);
        return p1 + p2;
    }
}
