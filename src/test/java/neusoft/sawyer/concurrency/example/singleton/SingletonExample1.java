package neusoft.sawyer.concurrency.example.singleton;

import neusoft.sawyer.concurrency.annotation.NotThreadSafe;

import java.util.Objects;

/**
 * Created by sawyer on 2018/7/5.
 * <p>
 * 懒汉模式：单例第一次使用时构造
 */
@NotThreadSafe
public class SingletonExample1 {

    // 私有构造函数
    private SingletonExample1() {
    }

    // 单例对象
    private static SingletonExample1 instance = null;

    // 静态工厂方法获取单例对象
    public static SingletonExample1 getInstance() {
        if (Objects.isNull(instance)) {
            instance = new SingletonExample1();
        }
        return instance;
    }
}
