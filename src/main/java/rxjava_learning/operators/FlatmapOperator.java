package rxjava_learning.operators;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import rx.Observable;
import rxjava_learning.Logger;

import javax.annotation.PostConstruct;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;
import static rx.Observable.just;

@Profile("flatmap")
@Component
public class FlatmapOperator {

    @PostConstruct
    public void init() throws InterruptedException {

        /*Observable.from(loadCustomers())
                .flatMap(customer -> Observable.from(customer.getOrders()))
                .subscribe(Logger::log);

        Observable.from(loadCustomers())
                .flatMapIterable(Customer::getOrders)
                .subscribe(Logger::log);
                */

        just(10L, 1L)
                .flatMap(x ->
                        just(x).delay(x, TimeUnit.SECONDS))
                .subscribe(Logger::log);

        TimeUnit.SECONDS.sleep(10);

        Logger.log("teste");

        just(DayOfWeek.SUNDAY, DayOfWeek.MONDAY)
                .flatMap(this::loadRecordsFor)
                .subscribe(Logger::log);

        /*just(DayOfWeek.SUNDAY, DayOfWeek.MONDAY)
                .concatMap(this::loadRecordsFor)
                .subscribe(Logger::log);*/
    }

    private List<Customer> loadCustomers() {
        Random random = new Random();
        return range(1, 500).boxed()
                .map(value -> {
                    List<Order> orders = random.ints(10, 1, 30)
                            .boxed().map(Order::new)
                            .collect(toList());

                    return new Customer("customer" + value, orders);
                }).collect(toList());
    }

    Observable<String> loadRecordsFor(DayOfWeek dow) {
        switch(dow) {
            case SUNDAY:
                return Observable
                        .interval(90, MILLISECONDS)
                        .take(5)
                        .map(i -> "Sun-" + i);
            case MONDAY:
                return Observable
                        .interval(65, MILLISECONDS)
                        .take(5)
                        .map(i -> "Mon-" + i);
            //...
            default:
                return Observable.empty();
        }
    }

    class Order {
        int id;

        Order(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "Order{" + "id=" + id + '}';
        }
    }

    class Customer {
        String name;
        List<Order> orders;

        Customer(String name, List<Order> orders) {
            this.name = name;
            this.orders = orders;
        }

        List<Order> getOrders() {
            return orders;
        }
    }
}
