package neusoft.sawyer.concurrency.example.publish;

import lombok.extern.slf4j.Slf4j;
import neusoft.sawyer.concurrency.annotation.NotThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * Created by sawyer on 2018/6/15.
 */
@Slf4j
@NotThreadSafe
public class UnsafePublish {

    private static final Logger LOGGER = LoggerFactory.getLogger(UnsafePublish.class);

    private String[] status = {"a", "b", "c"};

    public String[] getStatus() {
        return this.status;
    }

    public static void main(String[] args) {
        UnsafePublish unsafePublish = new UnsafePublish();
        LOGGER.info(Arrays.toString(unsafePublish.getStatus()));

        unsafePublish.status[0] = "d";
        LOGGER.info(Arrays.toString(unsafePublish.getStatus()));
    }
}
