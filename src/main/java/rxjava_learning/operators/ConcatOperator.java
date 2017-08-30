package rxjava_learning.operators;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import rx.Observable;
import rxjava_learning.Logger;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

@Profile("concat")
@Component
public class ConcatOperator {

    //concat
    @PostConstruct
    public void init() {

        Observable<Car> fromCache = loadFromCache();
        Observable<Car> fromDb = loadFromDb();

        Observable<Car> found = Observable
                .concat(fromCache, fromDb)
                .first();

        found.subscribe(Logger::log);


        Observable<String> alice = speak(
                "To be, or not to be: that is the question", 110);
        Observable<String> bob = speak(
                "Though this be madness, yet there is method in't", 90);
        Observable<String> jane = speak(
                "There are more things in Heaven and Earth, " +
                        "Horatio, than are dreamt of in your philosophy", 100);

        Observable
                .concat(
                        alice.map(w -> "Alice: " + w),
                        bob.map(w   -> "Bob:   " + w),
                        jane.map(w  -> "Jane:  " + w)
                )
                .subscribe(System.out::println);

    }

    private Observable<Car> loadFromDb() {
        return Observable.just(new Car("car from db")).delay(2000, MILLISECONDS);
    }

    private Observable<Car> loadFromCache() {
        return Observable.just(new Car("car from cache")).delay(300, MILLISECONDS);
    }

    Observable<String> speak(String quote, long millisPerChar) {
        String[] tokens = quote.replaceAll("[:,]", "").split(" ");
        Observable<String> words = Observable.from(tokens);
        Observable<Long> absoluteDelay = words
                .map(String::length)
                .map(len -> len * millisPerChar)
                .scan((total, current) -> total + current);
        return words
                .zipWith(absoluteDelay.startWith(0L), Pair::of)
                .flatMap(pair -> Observable.just(pair.getLeft())
                        .delay(pair.getRight(), MILLISECONDS));
    }



    public class Car {
        String name;

        public Car(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
