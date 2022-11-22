package com.example.cardealer.repositories;

import com.example.cardealer.domain.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    @Query(value = "select * from `car-dealer`.cars order by Rand() limit 1", nativeQuery = true)
    Optional<Car> getRandomEntity();

    Optional<List<Car>> findByMakeOrderByModelAscTravelledDistanceDesc(String model);

}
