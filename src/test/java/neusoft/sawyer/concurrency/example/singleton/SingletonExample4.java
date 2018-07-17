package neusoft.sawyer.concurrency.example.singleton;

import lombok.extern.slf4j.Slf4j;
import neusoft.sawyer.concurrency.annotation.NotThreadSafe;
import neusoft.sawyer.concurrency.annotation.RepeatExecute;
import org.junit.Assert;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Created by sawyer on 2018/7/5.
 * <p>
 * 懒汉模式：双重同步锁单例模式
 */
@Slf4j
@Component
@NotThreadSafe
public class SingletonExample4 {

    public int a = 1;

    // 私有构造函数
    // private SingletonExample4() {
    // }

    /*
     * instance = new SingletonExample4();
     *
     * 1. memory = allocate()    分配对象的内存空间
     * 2. ctorInstance()         初始化对象
     * 3. instance = memory      设置instance指向刚分配的内存
     *
     * JVM 和 CPU 优化，发生了指令重排
     * 1、3、2
     * */

    // 单例对象
    private static SingletonExample4 instance = null;

    // 静态工厂方法获取单例对象
    public static SingletonExample4 getInstance() {
        if (Objects.isNull(instance)) { // B - 判断有值
            synchronized (SingletonExample4.class) {
                if (Objects.isNull(instance)) {
                    instance = new SingletonExample4(); // A - 3
                }
            }
        }
        return instance;
    }

    @RepeatExecute
    public void invoke() {
        SingletonExample4 instance = SingletonExample4.getInstance();
        Assert.assertEquals(1, instance.a);
    }
}
