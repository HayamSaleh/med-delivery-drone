package com.deliverydrone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deliverydrone.model.DeliveryMedication;

@Repository
public interface DeliveryMedicationRepository extends JpaRepository<DeliveryMedication, Long> {

}
