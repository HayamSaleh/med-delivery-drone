package com.deliverydrone.service;

import java.util.List;

import com.deliverydrone.dto.DeliveryMedicationDto;

public interface MedicationLoadingService {

  static final float MIN_SAFE_BATTARY = 25f;
  static final String DRONE_BATTARY_UNDER_SAFE_LIMIT = "The drone's battery is below the minimum safe level for flight (%d%%). Please return the drone for recharging and try again later.";

  boolean bulkLoadMedicationByDroneId(Long id, List<DeliveryMedicationDto> deliveryMedicationsDtos);

  boolean loadMedicationByDroneId(Long id, DeliveryMedicationDto deliveryMedicationDto);

  boolean endLoadingByDroneId(Long droneId);

}
