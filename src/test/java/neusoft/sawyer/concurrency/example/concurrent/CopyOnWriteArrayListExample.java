package neusoft.sawyer.concurrency.example.concurrent;

import lombok.extern.slf4j.Slf4j;
import neusoft.sawyer.concurrency.annotation.RepeatExecute;
import neusoft.sawyer.concurrency.annotation.ThreadSafe;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by sawyer on 2018/7/23.
 */
@Slf4j
@Component
@ThreadSafe
public class CopyOnWriteArrayListExample {

    private static final List<Integer> list = new CopyOnWriteArrayList<>();

    @RepeatExecute
    void execute() {
        list.add(1);
    }

    void logList() {
        log.info("list size: {}", list.size());
    }
}
