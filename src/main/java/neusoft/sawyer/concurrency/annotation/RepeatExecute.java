package neusoft.sawyer.concurrency.annotation;

import java.lang.annotation.*;

/**
 * Created by sawyer on 2018/6/11.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface RepeatExecute {

    /*
    * 总执行次数
    * */
    int clientTotal() default 5000;

    /*
    * 同时并发最大线程数
    * */
    int threadTotal() default 200;
}
