package com.deliverydrone.service.impl;

import java.util.List;
import javax.naming.directory.InvalidAttributeValueException;
import javax.persistence.EntityNotFoundException;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.deliverydrone.dto.DroneDto;
import com.deliverydrone.enms.DroneState;
import com.deliverydrone.model.Drone;
import com.deliverydrone.model.DroneModel;
import com.deliverydrone.repository.DroneRepository;
import com.deliverydrone.service.DroneService;
import com.deliverydrone.utils.MapperUtils;

@Service
public class DroneServiceImpl implements DroneService {


  private static final String NO_DRONE_STATE_FOUND_WITH_VALUE = null;

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
    return mapperUtils.mapList(droneRepository.findAllByCurrentState(DroneState.IDLE),
        DroneDto.class);
  }

  @Override
  public DroneDto getDroneById(Long id) {
    return dozerMapper.map(getDroneEntityByID(id), DroneDto.class);
  }

  private Drone getDroneEntityByID(Long id) {
    Drone drone = droneRepository.findById(id).orElse(null);
    if (drone == null) {
      throw new EntityNotFoundException(DRONE_NOT_FOUND);
    }
    return drone;
  }

  @Override
  public Double checkDroneBatteryLevelById(Long id) throws EntityNotFoundException {
    DroneDto drone = getDroneById(id);
    return drone.getBatteryCurrentCapacity();
  }

  @Override
  public DroneDto addDrone(DroneDto droneDto) {
    Drone savedDrone = droneRepository.save(dozerMapper.map(droneDto, Drone.class));
    return dozerMapper.map(savedDrone, DroneDto.class);
  }

  @Override
  public DroneDto updateDrone(DroneDto droneDto, Long id) throws EntityNotFoundException {
    Drone existingDrone = getDroneEntityByID(id);
    existingDrone.setSerialNumber(droneDto.getSerialNumber());
    existingDrone.setBatteryCurrentCapacity(droneDto.getBatteryCurrentCapacity());
    existingDrone.setCurrentState(droneDto.getCurrentState());
    existingDrone.setModel(dozerMapper.map(droneDto.getModel(), DroneModel.class));

    return dozerMapper.map(droneRepository.save(existingDrone), DroneDto.class);
  }

  @Override
  public DroneDto updateDroneState(String droneState, Long id)
      throws InvalidAttributeValueException {
    DroneState updatedDroneState = DroneState.valueOf(droneState);
    if (updatedDroneState == null) {
      throw new InvalidAttributeValueException(NO_DRONE_STATE_FOUND_WITH_VALUE + ": " + droneState);
    }

    Drone existingDrone = getDroneEntityByID(id);
    existingDrone.setCurrentState(updatedDroneState);

    return dozerMapper.map(droneRepository.save(existingDrone), DroneDto.class);
  }

  @Override
  public boolean deleteDrone(Long id) {
    if (droneRepository.existsById(id)) {
      droneRepository.deleteById(id);
      return true;
    }

    throw new EntityNotFoundException(DRONE_NOT_FOUND);
  }

}
