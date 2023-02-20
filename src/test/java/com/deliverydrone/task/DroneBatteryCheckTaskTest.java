package com.deliverydrone.task;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.deliverydrone.dto.DroneDto;
import com.deliverydrone.service.DroneService;

@ExtendWith(MockitoExtension.class)
class DroneBatteryCheckTaskTest {

  @Mock
  private DroneService droneService;

  @InjectMocks
  private DroneBatteryCheckTask task;

  @Test
  void testCheckDroneBatteryLevels() {
	DroneDto drone = new DroneDto();
	drone.setId(1L);
	drone.setSerialNumber("ABC123");
	drone.setBatteryLevel(100f);

	when(droneService.getAllDrones()).thenReturn(Collections.singletonList(drone));

	task.checkDroneBatteryLevels();

	verify(droneService).getAllDrones();
  }
}
