package com.deliverydrone.service.impl;

import java.util.Date;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deliverydrone.controller.exception.DeliveryNotFoundException;
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
	List<DeliveryMedication> deliveryMedications = deliveryRepository.findDeliveryLoadByDroneId(droneId);
	return mapperUtils.mapList(deliveryMedications, DeliveryMedicationDto.class);
  }

  @Override
  public DeliveryDto createDelivery(Long droneId) {
	DroneDto droneDto = droneService.getDroneById(droneId);
	if (isDroneAvailableForNewDelivery(droneDto)) {
	  droneDto.setCurrentState(DroneState.LOADING);
	  return saveDelivery(new DeliveryDto(null, droneDto, DeliveryState.LOADING, new Date()));
	}

	throw new DeliveryNotFoundException(String.format(UNAVAILABLE_DRONE, droneId));
  }

  private boolean isDroneAvailableForNewDelivery(DroneDto droneDto) {
	return DroneState.IDLE.equals(droneDto.getCurrentState()) && isDroneBatteryLevelSafeToBeLoaded(droneDto);
  }

  private boolean isDroneBatteryLevelSafeToBeLoaded(DroneDto droneDto) {
	return droneDto.getBatteryLevel() >= MIN_ALLOWED_BATTARY_TO_BE_LOADED;
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

	throw new IllegalStateException(EMPTY_DELIVERY);
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
	throw new DeliveryNotFoundException(String.format(NO_OUT_FOR_DELIVERY, deliveryId));
  }

  private Delivery getDeliveryById(Long deliveryId) {
	Delivery delivery = deliveryRepository.findById(deliveryId).orElse(null);
	if (delivery == null) {
	  throw new DeliveryNotFoundException(deliveryId);
	}
	return delivery;
  }

}
