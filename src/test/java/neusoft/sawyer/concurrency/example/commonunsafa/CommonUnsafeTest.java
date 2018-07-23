package neusoft.sawyer.concurrency.example.commonunsafa;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by sawyer on 2018/7/17.
 */
@Slf4j
@Component
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class CommonUnsafeTest {

    @Resource
    private StringExample1 stringExample1;
    @Resource
    private DateFormatExample1 dateFormatExample1;
    @Resource
    private DateFormatExample2 dateFormatExample2;
    @Resource
    private ArrayListExample arrayListExample;

    /**
     * StringBuffer 和 StringBuilder 演示
     */
    @Test
    public void testStringExample() {
        {
            stringExample1.updateString();
        }

        {
            String result = StringExample1.stringBuilder.toString();
            log.info("StringBuilder result: {}", result);
            log.info("StringBuilder length: {}", result.length());
        }

        {
            String result = StringExample1.stringBuffer.toString();
            log.info("StringBuilder result: {}", result);
            log.info("StringBuilder length: {}", result.length());
        }
    }

    /**
     * DateFormat 线程不安全演示
     */
    @Test
    public void testDataFormatExample1() {
        this.dateFormatExample1.execute();
    }

    /**
     * JODA 日期安全类演示
     */
    @Test
    public void testDateFormatExample2() {
        this.dateFormatExample2.execute();
    }

    /*
     * ArrayList 线程不安全演示
     * */
    @Test
    public void testArrayListExample() {
        this.arrayListExample.execute();
        log.info("ArrayList size = {}", ArrayListExample.list.size());
    }
}
