package neusoft.sawyer.concurrency.example.commonunsafa;

import lombok.extern.slf4j.Slf4j;
import neusoft.sawyer.concurrency.annotation.NotThreadSafe;
import neusoft.sawyer.concurrency.annotation.RepeatExecute;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sawyer on 2018/7/17.
 */
@Slf4j
@Component
@NotThreadSafe
public class ArrayListExample {

    static List<Integer> list = new ArrayList<>();

    @RepeatExecute
    void execute() {
        list.add(1);
    }
}
