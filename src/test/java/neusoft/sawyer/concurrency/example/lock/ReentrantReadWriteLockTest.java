package neusoft.sawyer.concurrency.example.lock;

import lombok.extern.slf4j.Slf4j;
import neusoft.sawyer.concurrency.annotation.RepeatExecute;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by sawyer on 2019-05-20.
 */
@Slf4j
@Component
public class ReentrantReadWriteLockTest {

    private static final ReadWriteLock LOCK = new ReentrantReadWriteLock(true);
    private static final Lock READ_LOCK = LOCK.readLock();
    private static final Lock WRITE_LOCK = LOCK.writeLock();

    private static final Map<String, String> MAP = new HashMap<>();

    private String get(String key) {
        READ_LOCK.lock();
        try {
            return MAP.get(key);
        } finally {
            READ_LOCK.unlock();
        }
    }

    private void put(String key, String value) {
        WRITE_LOCK.lock();
        try {
            MAP.put(key, value);
        } finally {
            WRITE_LOCK.unlock();
        }
    }

    @RepeatExecute(clientTotal = 1000000, threadTotal = 10)
    void invoke() {
        this.read();
        this.write();
    }

    public String read() {
        return this.get("");
    }

    public void write() {
        put("", "");
    }
}
