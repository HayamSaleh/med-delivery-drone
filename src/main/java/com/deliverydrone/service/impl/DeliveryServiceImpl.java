// package com.deliverydrone.service.impl;
//
// import java.util.List;
// import org.dozer.DozerBeanMapper;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import com.deliverydrone.dto.DeliveryDto;
// import com.deliverydrone.dto.DeliveryMedicationDto;
// import com.deliverydrone.model.Delivery;
// import com.deliverydrone.model.DeliveryMedication;
// import com.deliverydrone.model.DeliveryState;
// import com.deliverydrone.model.Drone;
// import com.deliverydrone.model.Medication;
// import com.deliverydrone.repository.DeliveryRepository;
// import com.deliverydrone.repository.DroneStateRepository;
// import com.deliverydrone.service.DeliveryService;
// import com.deliverydrone.service.DroneService;
// import com.musalasoft.drone.dto.DroneDto;
// import com.musalasoft.drone.utils.MapperUtils;
//
// @Service
// public class DeliveryServiceImpl implements DeliveryService {
//
// @Autowired
// private DozerBeanMapper mapper;
//
// @Autowired
// private MapperUtils mapperUtils;
//
// @Autowired
// private DroneService droneService;
//
// @Autowired
// private DeliveryRepository deliveryRepository;
// @Autowired
// private DroneStateRepository droneStateRepository;
//
// @Override
// public DeliveryDto createDelivery(DeliveryDto deliveryDto, Long droneId) {
// Delivery delivery = new Delivery();
// Medication medication = new Medication();
// medication.setId(deliveryDto.getMedicationId());
// delivery.setDrone(new Drone(droneId));
// delivery.setDeliveryState(deliveryDto.getDeliveryState());
// return mapper.map(deliveryRepository.save(delivery), DeliveryDto.class);
// }
//
// @Override
// public List<DeliveryDto> getDeliveriesByDroneId(Long droneId) {
// return mapperUtils.mapList(deliveryRepository.findByDroneId(droneId), DeliveryDto.class);
//
// }
//
// @Override
// public List<DeliveryMedicationDto> getLoadedMedicationByDroneId(Long droneId) {
// List<DeliveryMedication> deliveryMedications =
// deliveryRepository.findInProgressDeliveryByDroneId(droneId);
// return mapperUtils.mapList(deliveryMedications, DeliveryMedicationDto.class);
// }
//
// @Override
// public void loadMedicationByDroneId(Long id, DeliveryMedicationDto deliveryMedicationsDto) {
// DroneDto drone = droneService.getDroneById(id);
// if (drone.getBatteryCurrentCapacity() >= 25) {
// Delivery delivery =
// deliveryRepository.findByDroneIdAndDeliveryState(id, DeliveryState.LOADING);
// DeliveryMedication deliveryMedication =
// mapper.map(deliveryMedicationsDto, DeliveryMedication.class);
// delivery.getDeliveryMedication().add(deliveryMedication);
// droneService.updateDrone(drone, id);
// deliveryRepository.save(delivery);
// }
// // handle message
//
// }
//
// @Override
// public void bulkLoadMedicationByDroneId(Long id,
// List<DeliveryMedicationDto> deliveryMedicationsDtos) {
// DroneDto drone = droneService.getDroneById(id);
// if (drone.getBatteryCurrentCapacity() >= 25) {
// Delivery delivery =
// deliveryRepository.findByDroneIdAndDeliveryState(id, DeliveryState.LOADING);
// List<DeliveryMedication> deliveryMedications =
// mapperUtils.mapList(deliveryMedicationsDtos, DeliveryMedication.class);
// delivery.getDeliveryMedication().addAll(deliveryMedications);
// drone.setCurrentState(droneStateRepository.findbyName("LOADING"));
// droneService.updateDrone(drone, id);
// deliveryRepository.save(delivery);
// }
// // handle message
// }
//
// }
