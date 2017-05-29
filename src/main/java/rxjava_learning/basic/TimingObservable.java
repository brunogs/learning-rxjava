package rxjava_learning.basic;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import rx.Observable;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MICROSECONDS;
import static rxjava_learning.Logger.log;

@Profile("timing")
@Component
public class TimingObservable {

    @PostConstruct
    public void init() {

        Observable.timer(1, TimeUnit.SECONDS)
                .subscribe(zero -> log("timer -> " + zero));

        Observable.interval(1_000_000 / 60, MICROSECONDS)
                .subscribe(i -> log("interval -> " + i));

    }
}
