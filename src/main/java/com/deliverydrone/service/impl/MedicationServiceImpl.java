package com.deliverydrone.service.impl;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deliverydrone.controller.exception.EntityAlreadyExistsException;
import com.deliverydrone.controller.exception.MedicationNotFoundException;
import com.deliverydrone.controller.exception.NotAllowedRequestException;
import com.deliverydrone.dto.MedicationDto;
import com.deliverydrone.model.Medication;
import com.deliverydrone.repository.MedicationRepository;
import com.deliverydrone.service.MedicationService;
import com.deliverydrone.utils.MapperUtils;

@Service
public class MedicationServiceImpl implements MedicationService {

  @Autowired
  private DozerBeanMapper dozerMapper;

  @Autowired
  private MapperUtils mapperUtils;

  @Autowired
  private MedicationRepository medicationRepository;

  @Override
  public List<MedicationDto> getAllMedications() {
	return mapperUtils.mapList(medicationRepository.findAll(), MedicationDto.class);
  }

  @Override
  public MedicationDto getMedicationById(Long id) {
	return dozerMapper.map(getMedicationEntityByID(id), MedicationDto.class);
  }

  private Medication getMedicationEntityByID(Long id) {
	return medicationRepository.findById(id).orElseThrow(() -> new MedicationNotFoundException(id));
  }

  @Override
  public MedicationDto addMedication(MedicationDto medicationDto) {
	validateMedicationData(medicationDto.getCode(), medicationDto.getName(), true);

	Medication savedMedication = medicationRepository.save(dozerMapper.map(medicationDto, Medication.class));
	return dozerMapper.map(savedMedication, MedicationDto.class);
  }

  @Override
  public MedicationDto updateMedication(MedicationDto medicationDto, Long id) {
	String updatedCode = medicationDto.getCode();
	validateMedicationData(updatedCode, medicationDto.getName(), false);

	Medication existingMedication = getMedicationEntityByID(id);
	if (medicationRepository.existsByCode(updatedCode) && !existingMedication.getCode().equals(updatedCode)) {
	  throw new EntityAlreadyExistsException(MEDICATION_WITH_SAME_CODE_EXISTS);
	}

	updateMedicationData(medicationDto, existingMedication);
	return dozerMapper.map(medicationRepository.save(existingMedication), MedicationDto.class);
  }

  private void validateMedicationData(String medicationCode, String medicationName, boolean create) {
	if (create && medicationRepository.existsByCode(medicationCode)) {
	  throw new EntityAlreadyExistsException(MEDICATION_WITH_SAME_CODE_EXISTS);
	}

	if (!medicationCode.matches(MEDICATION_CODE_PATTERN)) {
	  throw new NotAllowedRequestException(MEDICATION_WITH_INVALID_CODE);
	}

	if (!medicationName.matches(MEDICATION_NAME_PATTERN)) {
	  throw new NotAllowedRequestException(MEDICATION_WITH_INVALID_NAME);
	}
  }

  @Override
  public boolean deleteMedication(Long id) {
	if (medicationRepository.existsById(id)) {
	  medicationRepository.deleteById(id);
	  return true;
	}

	throw new MedicationNotFoundException(id);
  }

  @Override
  public List<Long> getValidMedicationByIdIn(List<Long> ids) {
	return medicationRepository.findAllByIdIn(ids);
  }

  private void updateMedicationData(MedicationDto medicationDto, Medication existingMedication) {
	existingMedication.setCode(medicationDto.getCode());
	existingMedication.setImage(medicationDto.getImage());
	existingMedication.setName(medicationDto.getName());
	existingMedication.setWeightInGrams(medicationDto.getWeightInGrams());
  }

}
