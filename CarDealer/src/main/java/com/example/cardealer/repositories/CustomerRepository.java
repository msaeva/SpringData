package com.example.cardealer.repositories;

import com.example.cardealer.domain.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<List<Customer>> findAllByOrderByBirthDateAscYoungDriverDesc();

    @Query(value = "select * from `car-dealer`.customers order by rand() limit 1", nativeQuery = true)
    Optional<Customer> getRandomEntity();

}
