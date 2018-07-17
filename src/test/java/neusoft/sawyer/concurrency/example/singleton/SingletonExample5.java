package neusoft.sawyer.concurrency.example.singleton;

import neusoft.sawyer.concurrency.annotation.ThreadSafe;

import java.util.Objects;

/**
 * Created by sawyer on 2018/7/5.
 */
@ThreadSafe
public class SingletonExample5 {

    // 私有构造函数
    private SingletonExample5() {
    }

    // 单例对象 volatile + 双重监测机制 -> 禁止指令重排
    private volatile static SingletonExample5 instance = null;

    // 静态工厂方法获取单例对象
    public static SingletonExample5 getInstance() {
        if (Objects.isNull(instance)) { // B - 判断有值
            synchronized (SingletonExample5.class) {
                if (Objects.isNull(instance)) {
                    instance = new SingletonExample5(); // A - 3
                }
            }
        }
        return instance;
    }
}
