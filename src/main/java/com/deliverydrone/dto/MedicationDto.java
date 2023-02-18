package com.deliverydrone.dto;

import javax.validation.constraints.Pattern;

public class MedicationDto {

  private Long id;
  @Pattern(regexp = "^[a-zA-Z0-9_-]+$")
  private String name;
  private double weight;
  private String code;
  private byte[] image;

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

  public double getWeight() {
    return weight;
  }

  public void setWeight(double weight) {
    this.weight = weight;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public byte[] getImage() {
    return image;
  }

  public void setImage(byte[] image) {
    this.image = image;
  }
}
