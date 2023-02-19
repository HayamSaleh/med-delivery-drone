package com.deliverydrone.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.deliverydrone.controller.exception.MedicationNotFoundException;
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
import com.deliverydrone.service.MedicationService;

@Service
public class LoadingServiceImpl implements LoadingService {

  @Autowired
  private DozerBeanMapper mapper;

  @Autowired
  private DeliveryRepository deliveryRepository;

  @Autowired
  private DeliveryService deliveryService;

  @Autowired
  private MedicationService medicationService;

  @Override
  public void bulkLoadMedicationByDroneId(Long droneId, List<DeliveryMedicationDto> deliveryMedicationsDtos) {
	DeliveryDto deliveryDto = getLoadingDeliveryByDroneId(droneId);

	if (isDroneBatteryLevelSafeToBeLoaded(deliveryDto.getDrone())) {
	  List<DeliveryMedicationDto> filteredDeliveryMedications = getFilteredDeliveryMedications(deliveryMedicationsDtos);

	  if (CollectionUtils.isEmpty(filteredDeliveryMedications)) {
		throw new MedicationNotFoundException("The required Medications are not found");
	  }

	  deliveryDto.getDeliveryMedication().addAll(filteredDeliveryMedications);
	  deliveryRepository.save(mapper.map(deliveryDto, Delivery.class));
	  return;
	}

	throw new NotAllowedRequestException(String.format(DRONE_BATTARY_UNDER_SAFE_LIMIT, MIN_SAFE_BATTARY));
  }

  @Override
  public void loadMedicationByDroneId(Long droneId, DeliveryMedicationDto deliveryMedicationsDto) {
	bulkLoadMedicationByDroneId(droneId, Arrays.asList(deliveryMedicationsDto));
  }

  private DeliveryDto getLoadingDeliveryByDroneId(Long droneId) {
	Delivery delivery = deliveryRepository.findByDroneIdAndDeliveryState(droneId, DeliveryState.LOADING);

	if (delivery == null) {
	  return deliveryService.createDelivery(droneId);
	}

	return mapper.map(delivery, DeliveryDto.class);
  }

  private List<DeliveryMedicationDto> getFilteredDeliveryMedications(List<DeliveryMedicationDto> deliveryMedications) {

	List<Long> ids = deliveryMedications.stream().map(deliveryMedication -> deliveryMedication.getMedication().getId())
		.collect(Collectors.toList());

	List<Long> validIds = medicationService.getValidMedicationByIdIn(ids);

	return deliveryMedications.stream()
		.filter(deliveryMedication -> validIds.contains(deliveryMedication.getMedication().getId()))
		.collect(Collectors.toList());
  }

  private boolean isDroneBatteryLevelSafeToBeLoaded(DroneDto droneDto) {
	return droneDto.getBatteryCurrentCapacity() >= MIN_SAFE_BATTARY;
  }

  @Override
  public void endLoadingByDroneId(Long droneId) {
	Delivery delivery = deliveryRepository.findByDroneIdAndDeliveryState(droneId, DeliveryState.LOADING);

	if (delivery == null || CollectionUtils.isEmpty(delivery.getDeliveryMedication())) {
	  throw new NotAllowedRequestException(DeliveryService.EMPTY_DELIVERY);
	}

	delivery.setDeliveryState(DeliveryState.LOADED);
	delivery.getDrone().setCurrentState(DroneState.LOADED);

	deliveryRepository.save(delivery);
  }

}
