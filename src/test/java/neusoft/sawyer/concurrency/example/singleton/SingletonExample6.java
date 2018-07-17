package neusoft.sawyer.concurrency.example.singleton;

import neusoft.sawyer.concurrency.annotation.ThreadSafe;

/**
 * Created by sawyer on 2018/7/5.
 */
@ThreadSafe
public class SingletonExample6 {

    // 私有构造函数
    private SingletonExample6() {

    }

    // 单例对象
    private static SingletonExample6 instance;

    // 静态代码块
    static {
        instance = new SingletonExample6();
    }

    // 静态工厂方法
    public static SingletonExample6 getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(getInstance());
        System.out.println(getInstance());
    }
}
