package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.model.Person;

@Repository
public interface PersonRepository  extends JpaRepository<Person, Long> {
}
