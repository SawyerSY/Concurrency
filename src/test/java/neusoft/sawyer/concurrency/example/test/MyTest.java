package neusoft.sawyer.concurrency.example.test;

import neusoft.sawyer.concurrency.example.publish.Escape;
import neusoft.sawyer.concurrency.example.singleton.SingletonExample4;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by sawyer on 2018/6/11.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MyTest {

    @Autowired
    private Escape escape;
    @Autowired
    private SingletonExample4 singletonExample4;

    @Test
    public void main() {
        singletonExample4.invoke();
    }
}
