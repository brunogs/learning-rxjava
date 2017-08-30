package rxjava_learning.operators;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import rx.Observable;
import rxjava_learning.Logger;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Profile("zip")
@Component
public class ZipOperator {

    @PostConstruct
    public void init() {

        //Transmit event only when both observable has messages
        Observable.zip(
                Observable.interval(1, TimeUnit.SECONDS).timestamp(),
                Observable.interval(500, TimeUnit.MILLISECONDS).timestamp(),
                (first, second) -> first.getTimestampMillis() + second.getTimestampMillis()
        ).subscribe(Logger::log);




    }

}
