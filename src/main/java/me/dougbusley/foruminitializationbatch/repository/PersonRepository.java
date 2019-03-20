package me.dougbusley.foruminitializationbatch.repository;

import me.dougbusley.foruminitializationbatch.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {}
