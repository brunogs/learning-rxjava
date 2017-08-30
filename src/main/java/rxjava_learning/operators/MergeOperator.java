package rxjava_learning.operators;


import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import rx.Observable;
import rxjava_learning.Logger;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Profile("merge")
@Component
public class MergeOperator {

    @PostConstruct
    public void init() {

        //Transmit event from any Observable merged
        Observable.merge(
                Observable.interval(1, TimeUnit.SECONDS).timestamp(),
                Observable.interval(500, TimeUnit.MILLISECONDS).timestamp()
        ).subscribe(Logger::log);

    }

}
