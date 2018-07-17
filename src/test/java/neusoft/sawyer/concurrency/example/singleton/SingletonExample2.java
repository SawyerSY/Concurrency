package neusoft.sawyer.concurrency.example.singleton;

import neusoft.sawyer.concurrency.annotation.ThreadSafe;

/**
 * Created by sawyer on 2018/7/5.
 * <p>
 * 恶汉模式：单例实例在类装载时创建
 * 1. 私有构造函数没有过多的处理
 * 2. 在实际处理中一定被使用
 */
@ThreadSafe
public class SingletonExample2 {

    // 私有构造函数
    private SingletonExample2() {

    }

    // 单例对象
    private static SingletonExample2 instance = new SingletonExample2();

    // 静态工厂方法
    public static SingletonExample2 getInstance() {
        return instance;
    }
}
