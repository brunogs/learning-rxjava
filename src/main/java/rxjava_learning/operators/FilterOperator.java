package rxjava_learning.operators;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import rx.Observable;

import javax.annotation.PostConstruct;

import static java.util.concurrent.TimeUnit.MICROSECONDS;
import static rxjava_learning.Logger.log;

@Profile("filter")
@Component
public class FilterOperator {

    @PostConstruct
    public void init() {

        Observable.interval(1_000_000 / 60, MICROSECONDS)
                .filter(value -> (value % 2) == 0)
                .subscribe(i -> log("interval -> " + i));

    }
}
