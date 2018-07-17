package neusoft.sawyer.concurrency.example.singleton;

import neusoft.sawyer.concurrency.annotation.Recommend;
import neusoft.sawyer.concurrency.annotation.ThreadSafe;

/**
 * Created by sawyer on 2018/7/5.
 * <p>
 * 枚举模式
 */
@Recommend
@ThreadSafe
public class SingletonExample7 {

    // 私有构造函数
    private SingletonExample7() {

    }

    public static SingletonExample7 getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton {
        INSTANCE;

        private SingletonExample7 singleton;

        // JVM 保证该方法绝对只被调用一次
        Singleton() {
            this.singleton = new SingletonExample7();
        }

        public SingletonExample7 getInstance() {
            return singleton;
        }
    }
}
