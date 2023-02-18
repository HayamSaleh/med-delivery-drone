package com.deliverydrone.service.impl;

import java.util.List;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.deliverydrone.dto.DroneStateDto;
import com.deliverydrone.model.DroneState;
import com.deliverydrone.repository.DroneStateRepository;
import com.deliverydrone.service.DroneStateService;
import com.deliverydrone.utils.MapperUtils;

@Service
public class DroneServiceStateImpl implements DroneStateService {

  @Autowired
  private DozerBeanMapper dozerMapper;

  @Autowired
  private MapperUtils mapperUtils;

  @Autowired
  private DroneStateRepository droneStateRepository;

  @Override
  public List<DroneStateDto> getAllDroneStates() {
    return mapperUtils.mapList(droneStateRepository.findAll(), DroneStateDto.class);
  }

  @Override
  public DroneStateDto getDroneStateById(Long id) throws EntityNotFoundException {
    DroneState droneState = droneStateRepository.findById(id);
    if (droneState == null) {
      throw new EntityNotFoundException(DRONE_STATE_NOT_FOUND);
    }
    return dozerMapper.map(droneState, DroneStateDto.class);
  }

  @Override
  public DroneStateDto addDroneState(DroneStateDto droneStateDto) {
    if (droneStateRepository.existsByName(droneStateDto.getName())) {
      DroneState savedDrone =
          droneStateRepository.save(dozerMapper.map(droneStateDto, DroneState.class));
      return dozerMapper.map(savedDrone, DroneStateDto.class);
    }

    throw new EntityExistsException(DRONE_STATE_NAME_EXISTS);
  }

  @Override
  public DroneStateDto updateDroneState(DroneStateDto droneState, Long id) {
    DroneState existingDroneState = droneStateRepository.findById(id);
    if (existingDroneState == null) {
      throw new EntityNotFoundException(DRONE_STATE_NOT_FOUND);
    }
    existingDroneState.setName(droneState.getName());

    return dozerMapper.map(droneStateRepository.save(existingDroneState), DroneStateDto.class);
  }

  @Override
  public boolean deleteDroneState(Long id) throws EntityNotFoundException {
    if (droneStateRepository.existsById(id)) {
      droneStateRepository.deleteById(id);
      return true;
    }

    throw new EntityNotFoundException(DRONE_STATE_NOT_FOUND);
  }


}
