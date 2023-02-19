package com.deliverydrone.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deliverydrone.model.DroneModel;

@Repository
public interface DroneModelRepository extends JpaRepository<DroneModel, Integer> {

  Optional<DroneModel> findById(Long id);

  boolean existsByName(String name);

  boolean existsById(Long id);

  void deleteById(Long id);

}
