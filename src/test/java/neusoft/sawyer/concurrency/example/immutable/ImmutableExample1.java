package neusoft.sawyer.concurrency.example.immutable;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import neusoft.sawyer.concurrency.annotation.ThreadSafe;

import java.util.Map;

/**
 * Created by sawyer on 2018/7/17.
 */
@Slf4j
@ThreadSafe
public class ImmutableExample1 {

    private static final Integer a = 1;
    private static final String b = "2";
    private static final Map<Integer, Integer> map = Maps.newHashMap();

    static {
        {
            map.put(1, 2);
            map.put(3, 4);
            map.put(5, 6);
        }

        {
            // map = Collections.unmodifiableMap(map);
        }
    }

    public static void main(String[] args) {
        // 不允许修改
        {
            // a = 2;
            // b = "3";
            // map = Maps.newHashMap();
        }

        // 允许修改值
        {
            map.put(1, 3);
        }

        // 打印
        {
            log.info("{}", map.get(1));
        }
    }

    private void test(final int a) {
        // a = 1;
    }
}
