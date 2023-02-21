package com.deliverydrone.service.impl;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deliverydrone.controller.exception.DroneNotFoundException;
import com.deliverydrone.controller.exception.EntityAlreadyExistsException;
import com.deliverydrone.controller.exception.NotAllowedRequestException;
import com.deliverydrone.dto.DroneDto;
import com.deliverydrone.enums.DroneState;
import com.deliverydrone.model.Drone;
import com.deliverydrone.model.DroneModel;
import com.deliverydrone.repository.DroneRepository;
import com.deliverydrone.service.DroneService;
import com.deliverydrone.utils.MapperUtils;

@Service
public class DroneServiceImpl implements DroneService {

  @Autowired
  private DozerBeanMapper dozerMapper;

  @Autowired
  private MapperUtils mapperUtils;

  @Autowired
  private DroneRepository droneRepository;

  @Override
  public List<DroneDto> getAllDrones() {
	return mapperUtils.mapList(droneRepository.findAll(), DroneDto.class);
  }

  @Override
  public List<DroneDto> getAllAvailableDrones() {
	return mapperUtils.mapList(droneRepository.findAllByCurrentState(DroneState.IDLE), DroneDto.class);
  }

  @Override
  public DroneDto getDroneById(Long id) {
	return dozerMapper.map(getDroneEntityByID(id), DroneDto.class);
  }

  private Drone getDroneEntityByID(Long id) {
	Drone drone = droneRepository.findById(id).orElse(null);
	if (drone == null) {
	  throw new DroneNotFoundException(id);
	}
	return drone;
  }

  @Override
  public Float checkDroneBatteryLevelById(Long id) {
	DroneDto drone = getDroneById(id);
	return drone.getBatteryLevel();
  }

  @Override
  public DroneDto addDrone(DroneDto droneDto) {
	if (droneRepository.existsBySerialNumber(droneDto.getSerialNumber())) {
	  Drone savedDrone = droneRepository.save(dozerMapper.map(droneDto, Drone.class));
	  return dozerMapper.map(savedDrone, DroneDto.class);
	}

	throw new EntityAlreadyExistsException(DRONE_WITH_SAME_SERIAL_NUMBER_EXISTS);
  }

  @Override
  public DroneDto updateDrone(DroneDto droneDto, Long id) {
	Drone existingDrone = getDroneEntityByID(id);
	String updatedSerialNumber = droneDto.getSerialNumber();
	if (!existingDrone.getSerialNumber().equals(updatedSerialNumber)
		&& droneRepository.existsBySerialNumber(updatedSerialNumber)) {
	  throw new EntityAlreadyExistsException(DRONE_WITH_SAME_SERIAL_NUMBER_EXISTS);
	}
	existingDrone.setSerialNumber(updatedSerialNumber);
	existingDrone.setBatteryLevel(droneDto.getBatteryLevel());
	existingDrone.setCurrentState(droneDto.getCurrentState());
	existingDrone.setModel(dozerMapper.map(droneDto.getModel(), DroneModel.class));

	return dozerMapper.map(droneRepository.save(existingDrone), DroneDto.class);
  }

  @Override
  public DroneDto updateDroneState(String droneState, Long id) {
	DroneState updatedDroneState = getDroneState(droneState);
	Drone existingDrone = getDroneEntityByID(id);
	existingDrone.setCurrentState(updatedDroneState);

	return dozerMapper.map(droneRepository.save(existingDrone), DroneDto.class);
  }

  private DroneState getDroneState(String droneState) {
	try {
	  return DroneState.valueOf(droneState.toUpperCase());
	} catch (IllegalArgumentException ex) {
	  throw new NotAllowedRequestException(String.format(NOT_ALLOWED_DRONE_STATE, droneState));
	}
  }

  @Override
  public boolean existsById(Long droneId) {
	return droneRepository.existsById(droneId);
  }

  @Override
  public boolean deleteDrone(Long id) {
	if (existsById(id)) {
	  droneRepository.deleteById(id);
	  return true;
	}

	throw new DroneNotFoundException(id);
  }

}
