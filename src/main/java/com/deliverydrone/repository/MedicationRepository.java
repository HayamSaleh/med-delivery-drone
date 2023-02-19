package com.deliverydrone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.deliverydrone.model.Medication;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long> {

  @Query("SELECT medication.id from Medication medication WHERE medication.id IN :ids")
  List<Long> findAllByIdIn(@Param("ids") List<Long> ids);

  boolean existsByCode(String code);

}
