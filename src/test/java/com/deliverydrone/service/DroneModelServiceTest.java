package com.deliverydrone.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.dozer.DozerBeanMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.deliverydrone.dto.DroneModelDto;
import com.deliverydrone.model.DroneModel;
import com.deliverydrone.repository.DroneModelRepository;
import com.deliverydrone.service.impl.DroneModelServiceImpl;
import com.deliverydrone.utils.MapperUtils;

@ExtendWith(MockitoExtension.class)
class DroneModelServiceTest {

  @Mock
  private DozerBeanMapper dozerMapper;

  @Mock
  private MapperUtils mapperUtils;
  @Mock
  private DroneModelRepository droneModelRepository;

  @InjectMocks
  private DroneModelServiceImpl droneModelService = new DroneModelServiceImpl();

  private DroneModelDto droneModelDto;

  private DroneModel droneModel;

  @BeforeEach
  void setup() {
	MockitoAnnotations.openMocks(this);

	droneModelDto = new DroneModelDto();
	droneModelDto.setId(1L);
	droneModelDto.setName("Model A");
	droneModelDto.setWeightLimitInGrams(300f);

	droneModel = new DroneModel();
	droneModel.setId(1L);
	droneModel.setName("Model A");
	droneModel.setWeightLimitInGrams(300f);
  }

  @Test
  void getAllDroneModels_NonEmptyModelsData_ReturnsDroneModelDto() {
	List<DroneModel> droneModels = new ArrayList<>();
	droneModels.add(droneModel);
	List<DroneModelDto> expected = new ArrayList<>();
	expected.add(droneModelDto);

	doReturn(droneModels).when(droneModelRepository).findAll();
	doReturn(expected).when(mapperUtils).mapList(droneModels, DroneModelDto.class);

	assertEquals(expected, droneModelService.getAllDroneModels(),
		"Expected and actual lists of DroneModelDto objects should match");

	verify(droneModelRepository).findAll();
	verify(mapperUtils).mapList(droneModels, DroneModelDto.class);
  }

  @Test
  void getDroneModelById_ValidId_ReturnsDroneModelDto() {
	long id = 1L;
	doReturn(Optional.of(droneModel)).when(droneModelRepository).findById(id);
	doReturn(droneModelDto).when(dozerMapper).map(droneModel, DroneModelDto.class);

	assertEquals(droneModelDto, droneModelService.getDroneModelById(id),
		"Expected and actual DroneModelDto objects should match");

	verify(droneModelRepository).findById(id);
	verify(dozerMapper).map(droneModel, DroneModelDto.class);
  }

  @Test
  void getDroneModelById_InvalidId_ThrowsEntityNotFoundException() {
	Long id = 1L;
	doReturn(Optional.empty()).when(droneModelRepository).findById(id);

	assertThatThrownBy(() -> droneModelService.getDroneModelById(id)).isInstanceOf(EntityNotFoundException.class);
  }

  @Test
  void addDroneModel_ValidDroneModelDto_ReturnsDroneModelDto() {
	doReturn(false).when(droneModelRepository).existsByName(droneModelDto.getName());
	doReturn(droneModel).when(dozerMapper).map(droneModelDto, DroneModel.class);
	doReturn(droneModelDto).when(dozerMapper).map(droneModel, DroneModelDto.class);
	doReturn(droneModel).when(droneModelRepository).save(any(DroneModel.class));

	DroneModelDto result = droneModelService.addDroneModel(droneModelDto);

	assertNotNull(result);
	assertEquals(result.getName(), droneModel.getName());
	assertEquals(result.getWeightLimitInGrams(), droneModel.getWeightLimitInGrams());
  }

  @Test
  void addDroneModel_DroneModelDtoWithNameExists_ThrowsEntityExistsException() {
	DroneModelDto droneModelDto = new DroneModelDto();
	droneModelDto.setName("Drone Model");
	doReturn(true).when(droneModelRepository).existsByName(droneModelDto.getName());

	assertThatThrownBy(() -> droneModelService.addDroneModel(droneModelDto)).isInstanceOf(EntityExistsException.class);
  }

  @Test
  void deleteDroneModel_WithValidId_DeletesDroneModel() {
	long id = 1L;
	doReturn(true).when(droneModelRepository).existsById(id);
	droneModelService.deleteDroneModel(id);
	verify(droneModelRepository).deleteById(id);
  }

  @Test
  void deleteDroneModel_WithInvalidId_ThrowsEntityNotFoundException() {
	long id = 1L;
	doReturn(false).when(droneModelRepository).existsById(id);
	assertThatThrownBy(() -> droneModelService.deleteDroneModel(id)).isInstanceOf(EntityNotFoundException.class)
		.hasMessage(DroneModelServiceImpl.DRONE_MODEL_NOT_FOUND);
  }
}
