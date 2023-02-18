package com.deliverydrone.service.impl;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.deliverydrone.dto.DeliveryDto;
import com.deliverydrone.dto.DeliveryMedicationDto;
import com.deliverydrone.dto.DroneDto;
import com.deliverydrone.enms.DeliveryState;
import com.deliverydrone.enms.DroneState;
import com.deliverydrone.model.Delivery;
import com.deliverydrone.model.DeliveryMedication;
import com.deliverydrone.repository.DeliveryRepository;
import com.deliverydrone.service.DeliveryService;
import com.deliverydrone.service.DroneService;
import com.deliverydrone.utils.MapperUtils;

@Service
public class DeliveryServiceImpl implements DeliveryService {


  private static final int MIN_ALLOWED_BATTARY_TO_BE_LOADED = 25;

  private static final String DRONE_CURRENTLY_BUSY =
      "The requested drone is currently busy with another delivery request";


  private static final String NO_DELIVERY_FOR_THE_DRONE_IS_READY_TO_BE_DELIVERED = null;
  private static final String DELIVERY_LOADING_IS_STILL_IN_PROGRESS = null;
  private static final String THERE_IS_NO_DELIVERY_OUT_FOR_DELIVERY_WITH_THIS_ID = null;

  @Autowired
  private DozerBeanMapper mapper;

  @Autowired
  private MapperUtils mapperUtils;

  @Autowired
  private DroneService droneService;

  @Autowired
  private DeliveryRepository deliveryRepository;


  @Override
  public List<DeliveryDto> getDeliveriesByDroneId(Long droneId) {
    return mapperUtils.mapList(deliveryRepository.findByDroneId(droneId), DeliveryDto.class);
  }

  @Override
  public List<DeliveryMedicationDto> getDeliveryLoadByDroneId(Long droneId) {
    List<DeliveryMedication> deliveryMedications =
        deliveryRepository.findDeliveryLoadByDroneId(droneId);
    return mapperUtils.mapList(deliveryMedications, DeliveryMedicationDto.class);
  }

  @Override
  public DeliveryDto createDelivery(Long droneId) {
    DroneDto droneDto = droneService.getDroneById(droneId);
    if (isDroneAvailableForNewDelivery(droneDto)) {
      droneDto.setCurrentState(DroneState.LOADING);
      return saveDelivery(new DeliveryDto(null, droneDto, DeliveryState.LOADING, new Date()));
    }

    // create custom exception
    throw new EntityNotFoundException(DRONE_CURRENTLY_BUSY);
  }

  private boolean isDroneAvailableForNewDelivery(DroneDto droneDto) {
    return DroneState.IDLE.equals(droneDto.getCurrentState())
        && isDroneBatteryLevelSafeToBeLoaded(droneDto);
  }

  private boolean isDroneBatteryLevelSafeToBeLoaded(DroneDto droneDto) {
    return droneDto.getBatteryCurrentCapacity() >= MIN_ALLOWED_BATTARY_TO_BE_LOADED;
  }

  private DeliveryDto saveDelivery(DeliveryDto deliveryDto) {
    Delivery delivery = mapper.map(deliveryDto, Delivery.class);
    return mapper.map(deliveryRepository.save(delivery), DeliveryDto.class);
  }

  @Override
  public boolean startDeliveryById(Long deliveryId) {
    Delivery delivery = getDeliveryById(deliveryId);
    if (DeliveryState.LOADED.equals(delivery.getDeliveryState())) {
      delivery.setDeliveryState(DeliveryState.IN_PROGRESS);
      delivery.getDrone().setCurrentState(DroneState.DELIVERING);
      deliveryRepository.save(delivery);
      return true;
    }

    throw new EntityNotFoundException(DELIVERY_LOADING_IS_STILL_IN_PROGRESS);
  }

  @Override
  public boolean markAsDeliveredById(Long deliveryId) {
    Delivery delivery = getDeliveryById(deliveryId);
    if (DeliveryState.IN_PROGRESS.equals(delivery.getDeliveryState())) {
      delivery.setDeliveryState(DeliveryState.DELIVERED);
      delivery.getDrone().setCurrentState(DroneState.DELIVERED);
      deliveryRepository.save(delivery);
      return true;
    }
    throw new EntityNotFoundException(THERE_IS_NO_DELIVERY_OUT_FOR_DELIVERY_WITH_THIS_ID);
  }

  private Delivery getDeliveryById(Long deliveryId) {
    Delivery delivery = deliveryRepository.findById(deliveryId).orElse(null);
    if (delivery == null) {
      throw new EntityNotFoundException(NO_DELIVERY_FOR_THE_DRONE_IS_READY_TO_BE_DELIVERED);
    }
    return delivery;
  }

}
