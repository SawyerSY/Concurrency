package neusoft.sawyer.concurrency.example.concurrent;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by sawyer on 2018/7/23.
 */
@Slf4j
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ConcurrentTest {

    @Autowired
    private CopyOnWriteArrayListExample copyOnWriteArrayListExample;

    @Test
    public void main() {
        this.copyOnWriteArrayListExample.execute();
        this.copyOnWriteArrayListExample.logList();
    }
}
