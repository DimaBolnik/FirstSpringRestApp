package ru.bolnik.springcourse.firstspringrestapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bolnik.springcourse.firstspringrestapp.models.Person;

public interface PeopleRepository extends JpaRepository<Person, Integer> {

}
