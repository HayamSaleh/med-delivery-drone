package com.deliverydrone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deliverydrone.model.DeliveryMedication;

@Repository
public interface DeliveryMedicationRepository extends JpaRepository<DeliveryMedication, Long> {

//  void saveAll(List<DeliveryMedicationDto> filteredDeliveryMedications);
//
//  List<Delivery> findByDroneId(Long droneId);
//
//  Delivery findByDroneIdAndDeliveryState(Long id, DeliveryState deliveryState);
//
//  @Query("SELECT delivery.deliveryMedications from Delivery delivery where delivery.drone.id = :id AND delivery.deliveryState IN ('LOADING', 'IN_PROGRESS')")
//  List<DeliveryMedication> findDeliveryLoadByDroneId(@Param("id") Long droneId);

}
