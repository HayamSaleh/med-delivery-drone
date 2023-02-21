package com.deliverydrone.controller;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.deliverydrone.dto.DeliveryDto;
import com.deliverydrone.dto.DeliveryMedicationDto;
import com.deliverydrone.service.DeliveryService;

@ExtendWith(MockitoExtension.class)
class DeliveryControllerTest {

  private static Long id = 1L;

  @Mock
  private DeliveryService deliveryService;

  @Test
  void getDeliveriesByDroneId_ValidDeliveryId_ReturnsDeliveryDtoList() {
	List<DeliveryDto> deliveries = new ArrayList<>();
	when(deliveryService.getDeliveriesByDroneId(id)).thenReturn(deliveries);

	DeliveryController controller = new DeliveryController();
	controller.deliveryService = deliveryService;

	ResponseEntity<List<DeliveryDto>> response = controller.getDeliveriesByDroneId(id);
	assertSame(HttpStatus.OK, response.getStatusCode());
	assertSame(deliveries, response.getBody());
  }

  @Test
  void getDeliveryItemsByDroneId_ValidDeliveryId_ReturnsDeliveryMedicationDtoList() {
	List<DeliveryMedicationDto> items = new ArrayList<>();
	when(deliveryService.getDeliveryLoadByDroneId(id)).thenReturn(items);

	DeliveryController controller = new DeliveryController();
	controller.deliveryService = deliveryService;

	ResponseEntity<List<DeliveryMedicationDto>> response = controller.getDeliveryItemsByDroneId(id);
	assertSame(HttpStatus.OK, response.getStatusCode());
	assertSame(items, response.getBody());
  }

  @Test
  void startDeliveryById_ValidDeliveryId_ReturnsConfirmationTrue() {
	when(deliveryService.startDeliveryById(id)).thenReturn(true);

	DeliveryController controller = new DeliveryController();
	controller.deliveryService = deliveryService;

	ResponseEntity<Boolean> response = controller.startdeliveryById(id);
	assertSame(HttpStatus.OK, response.getStatusCode());
	assertTrue(response.getBody());
  }

  @Test
  void markAsDeliveredById_ValidDeliveryId_ReturnsConfirmationTrue() {
	when(deliveryService.markAsDeliveredById(id)).thenReturn(false);

	DeliveryController controller = new DeliveryController();
	controller.deliveryService = deliveryService;

	ResponseEntity<Boolean> response = controller.markAsDeliveredById(id);
	assertSame(HttpStatus.OK, response.getStatusCode());
	assertTrue(response.getBody());
  }

}
