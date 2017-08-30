package rxjava_learning.operators;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import rx.Observable;
import rxjava_learning.Logger;

import javax.annotation.PostConstruct;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

@Profile("amb")
@Component
public class AMBOperator {

    @PostConstruct
    public void init() {

        Observable.amb(
                stream(100, 17, "S"),
                stream(200, 10, "F")
        ).subscribe(Logger::log);

    }

    private Observable<String> stream(int initialDelay, int interval, String name) {
        return Observable
                .interval(initialDelay, interval, MILLISECONDS)
                .map(x -> name + x)
                .doOnSubscribe(() ->
                        Logger.log("Subscribe to " + name))
                .doOnUnsubscribe(() ->
                        Logger.log("Unsubscribe from " + name));
    }
}
