package com.deliverydrone.service.impl;

import java.util.ArrayList;
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
import com.deliverydrone.model.DeliveryMedication;
import com.deliverydrone.repository.DeliveryMedicationRepository;
import com.deliverydrone.repository.DeliveryRepository;
import com.deliverydrone.service.DeliveryService;
import com.deliverydrone.service.MedicationLoadingService;
import com.deliverydrone.service.MedicationService;
import com.deliverydrone.utils.MapperUtils;

@Service
public class MedicationLoadingServiceImpl implements MedicationLoadingService {

  @Autowired
  private DozerBeanMapper mapper;

  @Autowired
  private MapperUtils mapperUtils;

  @Autowired
  private DeliveryRepository deliveryRepository;

  @Autowired
  private DeliveryMedicationRepository deliveryMedicationRepository;
  @Autowired
  private DeliveryService deliveryService;

  @Autowired
  private MedicationService medicationService;

  @Override
  public boolean bulkLoadMedicationByDroneId(Long droneId, List<DeliveryMedicationDto> deliveryMedicationsDtos) {
	DeliveryDto deliveryDto = getLoadingDeliveryByDroneId(droneId);
	if (isDroneBatteryLevelSafeToBeLoaded(deliveryDto.getDrone())) {
	  List<DeliveryMedicationDto> filteredDeliveryMedications = getFilteredDeliveryMedications(deliveryDto,
		  deliveryMedicationsDtos);

	  if (CollectionUtils.isEmpty(filteredDeliveryMedications)) {
		throw new MedicationNotFoundException();
	  }

	  if (exceededDroneDeliveryWeightLimit(deliveryDto, deliveryDto.getDeliveryMedications(),
		  filteredDeliveryMedications)) {
		throw new NotAllowedRequestException(
			"Cannot add the requested medications, as it will exceed the weight limit of the drone.");
	  }

	  deliveryMedicationRepository.saveAll(mapperUtils.mapList(filteredDeliveryMedications, DeliveryMedication.class));
	  return true;
	}

	throw new NotAllowedRequestException(String.format(DRONE_BATTARY_UNDER_SAFE_LIMIT, MIN_SAFE_BATTARY));
  }

  @Override
  public boolean loadMedicationByDroneId(Long droneId, DeliveryMedicationDto deliveryMedicationsDto) {
	return bulkLoadMedicationByDroneId(droneId, Arrays.asList(deliveryMedicationsDto));
  }

  @Override
  public boolean endLoadingByDroneId(Long droneId) {
	Delivery delivery = deliveryRepository.findByDroneIdAndDeliveryState(droneId, DeliveryState.LOADING);

	if (delivery == null || CollectionUtils.isEmpty(delivery.getDeliveryMedications())) {
	  throw new NotAllowedRequestException(DeliveryService.EMPTY_DELIVERY);
	}

	delivery.setDeliveryState(DeliveryState.LOADED);
	delivery.getDrone().setCurrentState(DroneState.LOADED);

	deliveryRepository.save(delivery);
	return true;
  }

  private boolean exceededDroneDeliveryWeightLimit(DeliveryDto deliveryDto,
	  List<DeliveryMedicationDto> existingMedications, List<DeliveryMedicationDto> newMedication) {
	Float expectedWeight = calculateMedicationWeight(existingMedications) + calculateMedicationWeight(newMedication);
	return expectedWeight > deliveryDto.getDrone().getModel().getWeightLimitInGrams();
  }

  private Float calculateMedicationWeight(List<DeliveryMedicationDto> medications) {
	Float currentDroneWeight = (float) 0;
	for (DeliveryMedicationDto medication : medications) {
	  currentDroneWeight += medication.getQuantity() * medication.getMedication().getWeightInGrams();
	}
	return currentDroneWeight;
  }

  private DeliveryDto getLoadingDeliveryByDroneId(Long droneId) {
	Delivery delivery = deliveryRepository.findByDroneIdAndDeliveryState(droneId, DeliveryState.LOADING);

	if (delivery == null) {
	  return deliveryService.createDelivery(droneId);
	}

	return mapper.map(delivery, DeliveryDto.class);
  }

  private List<DeliveryMedicationDto> getFilteredDeliveryMedications(DeliveryDto deliveryDto,
	  List<DeliveryMedicationDto> deliveryMedications) {

	List<Long> medicationIds = new ArrayList<>();
	for (DeliveryMedicationDto deliveryMedication : deliveryMedications) {
	  deliveryMedication.setDelivery(deliveryDto);
	  medicationIds.add(deliveryMedication.getMedication().getId());
	}

	List<Long> validIds = medicationService.getValidMedicationByIdIn(medicationIds);

	if (validIds.size() == medicationIds.size()) {
	  return deliveryMedications;
	}

	return deliveryMedications.stream()
		.filter(deliveryMedication -> validIds.contains(deliveryMedication.getMedication().getId()))
		.collect(Collectors.toList());
  }

  private boolean isDroneBatteryLevelSafeToBeLoaded(DroneDto droneDto) {
	return droneDto.getBatteryLevel() >= MIN_SAFE_BATTARY;
  }

}
