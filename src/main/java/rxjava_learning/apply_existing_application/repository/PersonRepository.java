package rxjava_learning.apply_existing_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rxjava_learning.apply_existing_application.domain.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
