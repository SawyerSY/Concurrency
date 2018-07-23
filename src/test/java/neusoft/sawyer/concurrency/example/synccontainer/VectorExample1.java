package neusoft.sawyer.concurrency.example.synccontainer;

import neusoft.sawyer.concurrency.annotation.NotThreadSafe;

import java.util.Vector;

/**
 * Created by sawyer on 2018/7/17.
 */
@NotThreadSafe
public class VectorExample1 {

    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();

        while (true) {

            for (int i = 0; i < 10; i++) {
                vector.add(i);
            }

            Thread thread1 = new Thread(() -> {
                for (int i = 0; i < vector.size(); i++) {
                    vector.remove(i);
                }
            });

            Thread thread2 = new Thread(() -> {
                for (int i = 0; i < vector.size(); i++) {
                    System.out.println(vector.get(i));
                }
            });

            thread1.start();
            thread2.start();
        }
    }
}
