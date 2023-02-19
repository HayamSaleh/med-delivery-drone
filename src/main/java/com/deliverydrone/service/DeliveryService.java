package com.deliverydrone.service;

import java.util.List;
import com.deliverydrone.dto.DeliveryDto;
import com.deliverydrone.dto.DeliveryMedicationDto;

public interface DeliveryService {

  static final int MIN_ALLOWED_BATTARY_TO_BE_LOADED = 25;
  static final String EMPTY_DELIVERY =
      "The Delivery does not exits or it doesn't contain any items to be delivered";
  static final String NO_OUT_FOR_DELIVERY = "Delivery with id %d is not out for delivery";
  static final String UNAVAILABLE_DRONE =
      "Drone with id %d is currently unavailable for new deliveries";

  List<DeliveryDto> getDeliveriesByDroneId(Long droneId);

  List<DeliveryMedicationDto> getDeliveryLoadByDroneId(Long id);

  DeliveryDto createDelivery(Long droneId);

  boolean startDeliveryById(Long deliveryId);

  boolean markAsDeliveredById(Long deliveryId);

}
