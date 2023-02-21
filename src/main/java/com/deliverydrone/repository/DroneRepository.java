package com.deliverydrone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deliverydrone.enums.DroneState;
import com.deliverydrone.model.Drone;

@Repository
public interface DroneRepository extends JpaRepository<Drone, Long> {

  List<Drone> findAllByCurrentState(DroneState state);

  boolean existsBySerialNumber(String serialNumber);

}
