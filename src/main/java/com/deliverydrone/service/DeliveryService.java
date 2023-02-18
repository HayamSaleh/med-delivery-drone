package com.deliverydrone.service;

import java.util.List;
import com.deliverydrone.dto.DeliveryDto;
import com.deliverydrone.dto.DeliveryMedicationDto;

public interface DeliveryService {

  List<DeliveryDto> getDeliveriesByDroneId(Long droneId);

  List<DeliveryMedicationDto> getDeliveryLoadByDroneId(Long id);

  DeliveryDto createDelivery(Long droneId);

  boolean startDeliveryById(Long deliveryId);

  boolean markAsDeliveredById(Long deliveryId);

}
