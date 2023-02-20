package com.deliverydrone.dto;

import com.deliverydrone.enums.DroneState;

public class DroneDto {

  private Long id;
  private String serialNumber;
  private DroneModelDto model;
  private Float batteryLevel;
  private DroneState currentState;

  public DroneDto() {
	super();
  }

  public DroneDto(Long id) {
	this.id = id;
  }

  public Long getId() {
	return id;
  }

  public void setId(Long id) {
	this.id = id;
  }

  public String getSerialNumber() {
	return serialNumber;
  }

  public void setSerialNumber(String serialNumber) {
	this.serialNumber = serialNumber;
  }

  public DroneModelDto getModel() {
	return model;
  }

  public void setModel(DroneModelDto model) {
	this.model = model;
  }

  public Float getBatteryLevel() {
	return batteryLevel;
  }

  public void setBatteryLevel(Float batteryLevel) {
	this.batteryLevel = batteryLevel;
  }

  public DroneState getCurrentState() {
	return currentState;
  }

  public void setCurrentState(DroneState currentState) {
	this.currentState = currentState;
  }

}
