package rxjava_learning.basic;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import rx.Observable;

import javax.annotation.PostConstruct;

import static rxjava_learning.Logger.log;

@Profile("subscribing-log")
@Component
public class SubscribingWithLog {

    @PostConstruct
    public void init() {
        Observable<Integer> ints = Observable.<Integer>create(subscriber -> {
            log("Create");
            subscriber.onNext(42);
            subscriber.onCompleted();
        }).cache();

        log("Starting");
        ints.subscribe(i -> log("Element A:" + i));
        ints.subscribe(i -> log("Element B:" + i));
        log("Exit");
    }

}
