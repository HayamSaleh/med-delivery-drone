package com.deliverydrone.service;

import java.util.List;

import com.deliverydrone.dto.DeliveryDto;
import com.deliverydrone.dto.DeliveryMedicationDto;

public interface DeliveryService {

  static final float MIN_ALLOWED_BATTARY_TO_BE_LOADED = 25f;
  static final String EMPTY_OR_INVALID_DELIVERY = "The request delivery does not exits or it is not in loading state.";
  static final String NO_OUT_FOR_DELIVERY = "Delivery with id %d is not out for delivery.";
  static final String UNAVAILABLE_DRONE = "Drone with id %d is currently unavailable for new deliveries.";

  List<DeliveryDto> getDeliveriesByDroneId(Long droneId);

  List<DeliveryMedicationDto> getDeliveryLoadByDroneId(Long id);

  DeliveryDto createDelivery(Long droneId);

  boolean startDeliveryById(Long deliveryId);

  boolean markAsDeliveredById(Long deliveryId);

}
