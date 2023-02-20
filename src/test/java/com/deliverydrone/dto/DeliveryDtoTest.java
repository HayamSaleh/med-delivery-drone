package com.deliverydrone.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Date;

import org.junit.jupiter.api.Test;

import com.deliverydrone.SetterAndGetterTest;
import com.deliverydrone.enums.DeliveryState;

class DeliveryDtoTest {

  @Test
  void testDefaultConstructor() {
	DeliveryDto deliveryDto = new DeliveryDto();
	assertNull(deliveryDto.getDrone());
	assertNull(deliveryDto.getDeliveryState());
	assertNull(deliveryDto.getStartTime());
  }

  @Test
  void testParametrizedConstructor() {
	DroneDto droneDto = new DroneDto();
	DeliveryState deliveryState = DeliveryState.DELIVERED;
	Date startTime = new Date();
	DeliveryDto deliveryDto = new DeliveryDto(droneDto, deliveryState, startTime);
	assertEquals(droneDto, deliveryDto.getDrone());
	assertEquals(deliveryState, deliveryDto.getDeliveryState());
	assertEquals(startTime, deliveryDto.getStartTime());
  }

  @Test
  void testFlatFileReaderMetadata_Parameters() throws Exception {
	SetterAndGetterTest.validateSetterAndGetterTest(DeliveryDto.class);
  }

}
