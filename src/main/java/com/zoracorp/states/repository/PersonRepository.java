package com.zoracorp.states.repository;

import com.zoracorp.states.domain.Persons;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Persons, Long> {
}
