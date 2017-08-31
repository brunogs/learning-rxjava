package rxjava_learning.apply_existing_application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import rx.Observable;
import rxjava_learning.Logger;
import rxjava_learning.apply_existing_application.domain.Person;
import rxjava_learning.apply_existing_application.repository.PersonRepository;

import javax.annotation.PostConstruct;
import java.util.List;

import static rx.Observable.defer;
import static rx.Observable.from;

@Profile("composing")
@Component
public class ComposingObservable {

    @Autowired
    private PersonRepository personRepository;

    @PostConstruct
    public void init() {
        allPeople(0)
                .take(6)
                .subscribe(Logger::log);
    }

    private Observable<Person> allPeople(int initialPage) {
        return defer(() -> from(listPeople(initialPage)))
                .concatWith(defer(() ->
                        allPeople(initialPage + 1)));
    }

    private List<Person> listPeople(int initialPage) {
        PageRequest pageRequest = new PageRequest(initialPage, 4);
        return personRepository.findAll(pageRequest).getContent();
    }

}
