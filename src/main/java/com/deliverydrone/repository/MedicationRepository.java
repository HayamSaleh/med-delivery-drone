package com.deliverydrone.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.deliverydrone.model.Medication;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long> {

  List<Long> findAllByIdIn(List<Long> ids);

  boolean existsByCode(String code);

}
