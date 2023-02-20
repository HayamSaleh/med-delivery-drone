package com.deliverydrone.service.impl;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deliverydrone.dto.DroneModelDto;
import com.deliverydrone.model.DroneModel;
import com.deliverydrone.repository.DroneModelRepository;
import com.deliverydrone.service.DroneModelService;
import com.deliverydrone.utils.MapperUtils;

@Service
public class DroneModelServiceImpl implements DroneModelService {

  @Autowired
  private DozerBeanMapper dozerMapper;

  @Autowired
  private MapperUtils mapperUtils;

  @Autowired
  private DroneModelRepository droneModelRepository;

  @Override
  public List<DroneModelDto> getAllDroneModels() {
	return mapperUtils.mapList(droneModelRepository.findAll(), DroneModelDto.class);
  }

  @Override
  public DroneModelDto getDroneModelById(Long id) {
	DroneModel droneModel = droneModelRepository.findById(id)
		.orElseThrow(() -> new EntityNotFoundException(DRONE_MODEL_NOT_FOUND));

	return dozerMapper.map(droneModel, DroneModelDto.class);
  }

  @Override
  public DroneModelDto addDroneModel(DroneModelDto droneModelDto) {
	if (!droneModelRepository.existsByName(droneModelDto.getName())) {
	  return dozerMapper.map(droneModelRepository.save(dozerMapper.map(droneModelDto, DroneModel.class)),
		  DroneModelDto.class);
	}

	throw new EntityExistsException(DRONE_MODEL_NAME_EXISTS);
  }

  @Override
  public DroneModelDto updateDroneModel(DroneModelDto droneModel, Long id) {
	DroneModel existingDroneModel = droneModelRepository.findById(id)
		.orElseThrow(() -> new EntityNotFoundException(DRONE_MODEL_NOT_FOUND));

	existingDroneModel.setName(droneModel.getName());
	existingDroneModel.setWeightLimitInGrams(droneModel.getWeightLimitInGrams());

	return dozerMapper.map(droneModelRepository.save(existingDroneModel), DroneModelDto.class);
  }

  @Override
  public boolean deleteDroneModel(Long id) throws EntityNotFoundException {
	if (droneModelRepository.existsById(id)) {
	  droneModelRepository.deleteById(id);
	  return true;
	}

	throw new EntityNotFoundException(DRONE_MODEL_NOT_FOUND);
  }

}
