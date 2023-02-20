package com.deliverydrone.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.deliverydrone.SetterAndGetterTest;

class DroneDtoTest {

  @Test
  void testDefaultConstructor() {
	DroneDto droneDto = new DroneDto();
	assertNull(droneDto.getBatteryLevel());
	assertNull(droneDto.getCurrentState());
	assertNull(droneDto.getModel());
  }

  @Test
  void testParametrizedConstructor() {
	Long id = 1L;
	DroneDto droneDto = new DroneDto(id);
	assertEquals(droneDto.getId(), id);
  }

  @Test
  void testFlatFileReaderMetadata_Parameters() throws Exception {
	SetterAndGetterTest.validateSetterAndGetterTest(DroneDto.class);
  }

}
