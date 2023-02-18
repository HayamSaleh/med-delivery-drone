package com.deliverydrone.dto;

public class DroneModelDto {

  private Long id;
  private String name;
  private float weightLimitInGrams;
  private Integer batteryCapacity;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public float getWeightLimitInGrams() {
    return weightLimitInGrams;
  }

  public void setWeightLimitInGrams(float weightLimitInGrams) {
    this.weightLimitInGrams = weightLimitInGrams;
  }

  public Integer getBatteryCapacity() {
    return batteryCapacity;
  }

  public void setBatteryCapacity(Integer batteryCapacity) {
    this.batteryCapacity = batteryCapacity;
  }
}
