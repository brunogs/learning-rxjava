package rxjava_learning.basic;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import rx.Observable;
import rx.Subscriber;

import javax.annotation.PostConstruct;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

@Profile("unsubscribe")
@Component
public class UnsubscribingObservable {

    @PostConstruct
    public void init() {

        Subscriber<String> messageSubscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {}

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(String message) {
                System.out.println(message);
                if (message.contains("150")) {
                    unsubscribe();
                }
            }
        };

        Observable.from(loadMessages())
                .subscribe(messageSubscriber);
    }

    private List<String> loadMessages() {
        return range(1, 300).boxed()
                .map(value -> Thread.currentThread().getName() + " Message number: " + value)
                .collect(toList());
    }
}
