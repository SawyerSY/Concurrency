package neusoft.sawyer.concurrency.example.commonunsafa;

import lombok.extern.slf4j.Slf4j;
import neusoft.sawyer.concurrency.annotation.RepeatExecute;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by sawyer on 2018/7/17.
 */
@Slf4j
@Component
public class DateFormatExample1 {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @RepeatExecute
    void execute() {
        try {
            simpleDateFormat.parse("2018-02-08");
        } catch (ParseException e) {
            log.error("parse date exception", e);
        }
    }
}
