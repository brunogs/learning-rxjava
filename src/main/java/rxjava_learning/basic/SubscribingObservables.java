package rxjava_learning.basic;

import org.springframework.stereotype.Component;
import rx.Observable;

import javax.annotation.PostConstruct;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;

@Component
public class SubscribingObservables {

    @PostConstruct
    public void init() {
        Observable.from(loadMessages())
                .subscribe(
                        (String message) -> System.out.println(message),
                        Throwable::printStackTrace
                );
    }

    private List<String> loadMessages() {
        return range(1, 300).boxed()
                .map(value -> Thread.currentThread().getName() + " Message number: " + value)
                .collect(toList());
    }

}
