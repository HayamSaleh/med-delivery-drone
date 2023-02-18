package com.deliverydrone.service.impl;

import java.util.Arrays;
import java.util.List;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import com.deliverydrone.controller.exception.NotAllowedRequestException;
import com.deliverydrone.dto.DeliveryDto;
import com.deliverydrone.dto.DeliveryMedicationDto;
import com.deliverydrone.dto.DroneDto;
import com.deliverydrone.enms.DeliveryState;
import com.deliverydrone.enms.DroneState;
import com.deliverydrone.model.Delivery;
import com.deliverydrone.repository.DeliveryRepository;
import com.deliverydrone.service.DeliveryService;
import com.deliverydrone.service.LoadingService;

@Service
public class LoadingServiceImpl implements LoadingService {

  @Autowired
  private DozerBeanMapper mapper;

  @Autowired
  private DeliveryRepository deliveryRepository;

  @Autowired
  private DeliveryService deliveryService;

  @Override
  public void bulkLoadMedicationByDroneId(Long droneId,
      List<DeliveryMedicationDto> deliveryMedicationsDtos) {
    Delivery delivery =
        deliveryRepository.findByDroneIdAndDeliveryState(droneId, DeliveryState.LOADING);
    DeliveryDto deliveryDto = delivery == null ? deliveryService.createDelivery(droneId)
        : mapper.map(delivery, DeliveryDto.class);

    if (isDroneBatteryLevelSafeToBeLoaded(deliveryDto.getDrone())) {
      deliveryDto.getDeliveryMedication().addAll(deliveryMedicationsDtos);
      // is drone data updated
      deliveryRepository.save(mapper.map(deliveryDto, Delivery.class));
    } else {
      throw new NotAllowedRequestException(
          String.format(DRONE_BATTARY_UNDER_SAFE_LIMIT, MIN_SAFE_BATTARY));
    }
  }

  @Override
  public void loadMedicationByDroneId(Long droneId, DeliveryMedicationDto deliveryMedicationsDto) {
    bulkLoadMedicationByDroneId(droneId, Arrays.asList(deliveryMedicationsDto));
  }

  private boolean isDroneBatteryLevelSafeToBeLoaded(DroneDto droneDto) {
    return droneDto.getBatteryCurrentCapacity() >= MIN_SAFE_BATTARY;
  }

  @Override
  public void endLoadingByDroneId(Long droneId) {
    Delivery delivery =
        deliveryRepository.findByDroneIdAndDeliveryState(droneId, DeliveryState.LOADING);

    if (CollectionUtils.isEmpty(delivery.getDeliveryMedication())) {
      throw new IllegalStateException(DeliveryService.EMPTY_DELIVERY);
    }

    delivery.setDeliveryState(DeliveryState.LOADED);
    delivery.getDrone().setCurrentState(DroneState.LOADED);

    deliveryRepository.save(delivery);
  }

}
