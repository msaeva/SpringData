package com.example.cardealer.repositories;

import com.example.cardealer.domain.entities.Supplier;
import com.example.cardealer.domain.entities.dtos.supplier.LocalSupplierDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    @Query(value = "select * from `car-dealer`.suppliers order by RAND() LIMIT 1", nativeQuery = true)
    Optional<Supplier> getRandomEntity();

    @Query("select new com.example.cardealer.domain.entities.dtos.supplier.LocalSupplierDto(" +
            "s.id, s.name, count(p.id)) " +
            "FROM Supplier s " +
            "JOIN s.parts p " +
            "WHERE s.isImporter = false " +
            "GROUP BY s.id " +
            "ORDER BY count(p.id) DESC")
    Optional<List<LocalSupplierDto>> getLocalSupplierPartCount();

}

