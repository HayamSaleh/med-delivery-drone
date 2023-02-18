package com.deliverydrone.service.impl;

import java.util.Arrays;
import java.util.List;
import javax.activity.InvalidActivityException;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
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

  private static final int MIN_ALLOWED_BATTARY_TO_BE_LOADED = 25;

  private static final String DRONE_BATTARY_UNDER_OK_LIMIT = null;

  private static final String EMPTY_DRONE = null;

  @Autowired
  private DozerBeanMapper mapper;

  @Autowired
  private DeliveryRepository deliveryRepository;

  @Autowired
  private DeliveryService deliveryService;

  @Override
  public void bulkLoadMedicationByDroneId(Long droneId,
      List<DeliveryMedicationDto> deliveryMedicationsDtos) throws InvalidActivityException {
    Delivery delivery =
        deliveryRepository.findByDroneIdAndDeliveryState(droneId, DeliveryState.LOADING);
    DeliveryDto deliveryDto = delivery == null ? deliveryService.createDelivery(droneId)
        : mapper.map(delivery, DeliveryDto.class);

    if (isDroneBatteryLevelSafeToBeLoaded(deliveryDto.getDrone())) {
      deliveryDto.getDeliveryMedication().addAll(deliveryMedicationsDtos);
      // is drone data updated
      deliveryRepository.save(mapper.map(deliveryDto, Delivery.class));
    } else {
      throw new InvalidActivityException(DRONE_BATTARY_UNDER_OK_LIMIT);
    }
  }

  @Override
  public void loadMedicationByDroneId(Long droneId, DeliveryMedicationDto deliveryMedicationsDto)
      throws InvalidActivityException {
    bulkLoadMedicationByDroneId(droneId, Arrays.asList(deliveryMedicationsDto));
  }

  private boolean isDroneBatteryLevelSafeToBeLoaded(DroneDto droneDto) {
    return droneDto.getBatteryCurrentCapacity() >= MIN_ALLOWED_BATTARY_TO_BE_LOADED;
  }

  @Override
  public void endLoadingByDroneId(Long droneId) throws InvalidActivityException {
    Delivery delivery =
        deliveryRepository.findByDroneIdAndDeliveryState(droneId, DeliveryState.LOADING);

    if (CollectionUtils.isEmpty(delivery.getDeliveryMedication())) {
      throw new InvalidActivityException(EMPTY_DRONE);
    }

    delivery.setDeliveryState(DeliveryState.LOADED);
    delivery.getDrone().setCurrentState(DroneState.LOADED);

    deliveryRepository.save(delivery);
  }

}
