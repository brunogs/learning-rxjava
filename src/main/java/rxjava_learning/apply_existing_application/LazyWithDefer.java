package rxjava_learning.apply_existing_application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import rx.Observable;
import rxjava_learning.Logger;
import rxjava_learning.apply_existing_application.domain.Person;
import rxjava_learning.apply_existing_application.repository.PersonRepository;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Profile("defer")
@Component
public class LazyWithDefer {

    @Autowired
    private PersonRepository personRepository;

    @PostConstruct
    public void init() throws InterruptedException {

        listPeople().subscribe(Logger::log);
    }

    public Observable<Person> listPeople() {
        return Observable.defer(() -> {
            Logger.log("listPeople start");
            return Observable.from(personRepository.findAll());
        });
    }

}
