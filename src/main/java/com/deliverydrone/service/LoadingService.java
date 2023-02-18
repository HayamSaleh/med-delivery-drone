package com.deliverydrone.service;

import java.util.List;
import javax.activity.InvalidActivityException;
import com.deliverydrone.dto.DeliveryMedicationDto;

public interface LoadingService {

  void bulkLoadMedicationByDroneId(Long id, List<DeliveryMedicationDto> deliveryMedicationsDtos)
      throws InvalidActivityException;

  void loadMedicationByDroneId(Long id, DeliveryMedicationDto deliveryMedicationDto)
      throws InvalidActivityException;

  void endLoadingByDroneId(Long droneId) throws InvalidActivityException;

}
