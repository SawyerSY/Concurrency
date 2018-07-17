package neusoft.sawyer.concurrency.example.immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

/**
 * Created by sawyer on 2018/7/17.
 */
public class ImmutableExample2 {

    public static final ImmutableList<Integer> list = ImmutableList.of(1, 2, 3);

    public static final ImmutableSet<Integer> set = ImmutableSet.copyOf(list);

    public static final ImmutableMap<Integer, Integer> map1 = ImmutableMap.of(1, 2, 3, 4);

    public static final ImmutableMap<Integer, Integer> map2 = ImmutableMap.<Integer, Integer>builder().put(1, 2).put(3, 4).build();

    public static void main(String[] args) {
        list.add(4);
        set.add(4);
        map1.put(1, 3);
    }
}
