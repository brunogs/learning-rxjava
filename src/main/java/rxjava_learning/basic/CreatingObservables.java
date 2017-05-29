package rxjava_learning.basic;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import rx.Observable;
import rx.Observer;

import javax.annotation.PostConstruct;

import static java.util.stream.IntStream.range;

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

    /*
     * Observable.just(x), emits a single value x and immediately completes afterward, might look like this:
     */
    static <T> Observable just(T x) {
        return Observable.create(subscriber -> {
           subscriber.onNext(x);
           subscriber.onCompleted();
        });
    }


    static <T> Observable never() {
        return Observable.create(subscriber -> {
        });
    }

    static <T> Observable empty() {
        return Observable.create(Observer::onCompleted);
    }

    static <Integer> Observable never(int start, int count) {
        return Observable.create(subscriber -> {
            range(start, count)
                    .forEach(subscriber::onNext);
            subscriber.onCompleted();
        });
    }



}
