package com.deliverydrone.service;

import java.util.List;
import com.deliverydrone.dto.DeliveryMedicationDto;

public interface LoadingService {
  static final int MIN_SAFE_BATTARY = 25;

  static final String DRONE_BATTARY_UNDER_SAFE_LIMIT =
      "The drone's battery is below the minimum safe level for flight (%d%%). Please return the drone for recharging and try again later.";

  void bulkLoadMedicationByDroneId(Long id, List<DeliveryMedicationDto> deliveryMedicationsDtos);

  void loadMedicationByDroneId(Long id, DeliveryMedicationDto deliveryMedicationDto);

  void endLoadingByDroneId(Long droneId);

}
