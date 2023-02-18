package com.deliverydrone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.deliverydrone.model.DroneState;

@Repository
public interface DroneStateRepository extends JpaRepository<DroneState, Integer> {

  DroneState findById(Long id);

  boolean existsById(Long id);

  boolean existsByName(String name);

  void deleteById(Long id);

}
