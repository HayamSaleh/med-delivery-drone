package com.deliverydrone.task;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.deliverydrone.dto.DroneDto;
import com.deliverydrone.service.DroneService;

@Component
public class DroneBatteryCheckTask {
  private static final String BATTERY_LOG_MESSAGE = "Drone %d with serial number %s battery level:  %.0f";
  private static final int BATTERY_WARNING_THRESHOLD = 300000;
  private static final Logger LOGGER = LoggerFactory.getLogger(DroneBatteryCheckTask.class);

  @Autowired
  private DroneService droneService;

  @Scheduled(fixedDelay = BATTERY_WARNING_THRESHOLD)
  public void checkDroneBatteryLevels() {
	List<DroneDto> drones = droneService.getAllDrones();
	for (DroneDto drone : drones) {
	  LOGGER.info(String.format(BATTERY_LOG_MESSAGE, drone.getId(), drone.getSerialNumber(), drone.getBatteryLevel()));
	}
  }
}
