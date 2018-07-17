package neusoft.sawyer.concurrency.example.singleton;

import neusoft.sawyer.concurrency.annotation.NotRecommend;
import neusoft.sawyer.concurrency.annotation.ThreadSafe;

import java.util.Objects;

/**
 * Created by sawyer on 2018/7/5.
 */
@ThreadSafe
@NotRecommend
public class SingletonExample3 {

    // 私有构造函数
    private SingletonExample3() {
    }

    // 单例对象
    private static SingletonExample3 instance = null;

    // 静态工厂方法获取单例对象
    public static synchronized SingletonExample3 getInstance() {
        if (Objects.isNull(instance)) {
            instance = new SingletonExample3();
        }
        return instance;
    }
}
