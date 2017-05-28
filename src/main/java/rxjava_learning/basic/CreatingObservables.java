package rxjava_learning.basic;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import rx.Observable;

import javax.annotation.PostConstruct;

@Profile("create")
@Component
public class CreatingObservables {

    @PostConstruct
    public void init() {

        Observable<String> justObservable = Observable.just("simple message");

        Observable<String> fromObservable = Observable.from(new String[]{"message 1", "message 2", "message 3"});

        Observable<Integer> rangeObservable = Observable.range(1, 10);

        Observable<String> emptyObservable = Observable.empty();

        Observable<String> neverObservable = Observable.never();

        Observable<String> errorObservable = Observable.error(new IllegalStateException("invalid observable"));

    }
}
