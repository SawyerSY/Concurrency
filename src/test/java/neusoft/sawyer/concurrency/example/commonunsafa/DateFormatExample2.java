package neusoft.sawyer.concurrency.example.commonunsafa;

import lombok.extern.slf4j.Slf4j;
import neusoft.sawyer.concurrency.annotation.RepeatExecute;
import neusoft.sawyer.concurrency.annotation.ThreadSafe;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

/**
 * Created by sawyer on 2018/7/17.
 */
@Slf4j
@Component
@ThreadSafe
public class DateFormatExample2 {

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyyMMdd");

    @RepeatExecute
    void execute() {
        DateTime dateTime = dateTimeFormatter.parseDateTime("20180208");
        log.info("dateTime: {}", dateTime.toDate());
    }
}
