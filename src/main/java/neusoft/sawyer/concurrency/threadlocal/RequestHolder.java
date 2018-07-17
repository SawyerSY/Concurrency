package neusoft.sawyer.concurrency.threadlocal;

/**
 * Created by sawyer on 2018/7/17.
 */
public class RequestHolder {

    public static final ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    public static void setId(Long id) {
        requestHolder.set(id);
    }

    public static Long getId() {
        return requestHolder.get();
    }

    public static void remove() {
        requestHolder.remove();
    }
}
