package com.deliverydrone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.deliverydrone.enms.DeliveryState;
import com.deliverydrone.model.Delivery;
import com.deliverydrone.model.DeliveryMedication;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

  List<Delivery> findByDroneId(Long droneId);

  Delivery findByDroneIdAndDeliveryState(Long id, DeliveryState deliveryState);

  @Query("SELECT delivery.deliveryMedications from Delivery delivery where delivery.drone.id = :id AND delivery.deliveryState IN ('LOADING', 'LOADED', 'IN_PROGRESS')")
  List<DeliveryMedication> findDeliveryLoadByDroneId(@Param("id") Long droneId);

}
