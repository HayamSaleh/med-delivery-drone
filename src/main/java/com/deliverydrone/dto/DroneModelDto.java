package com.deliverydrone.dto;

public class DroneModelDto {

  private Long id;
  private String name;
  private Float weightLimitInGrams;

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

  public Float getWeightLimitInGrams() {
    return weightLimitInGrams;
  }

  public void setWeightLimitInGrams(Float weightLimitInGrams) {
    this.weightLimitInGrams = weightLimitInGrams;
  }
}
