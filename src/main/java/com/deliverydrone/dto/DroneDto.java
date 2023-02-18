package com.deliverydrone.dto;

public class DroneDto {

  private Long id;
  private String serialNumber;
  private DroneModelDto model;
  private Double batteryCurrentCapacity;
  private DroneStateDto currentState;

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

  public Double getBatteryCurrentCapacity() {
    return batteryCurrentCapacity;
  }

  public void setBatteryCurrentCapacity(Double batteryCurrentCapacity) {
    this.batteryCurrentCapacity = batteryCurrentCapacity;
  }

  public DroneStateDto getCurrentState() {
    return currentState;
  }

  public void setCurrentState(DroneStateDto currentState) {
    this.currentState = currentState;
  }

}
