package com.deliverydrone.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "drone_model")
public class DroneModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "weight_limit_grams", nullable = false)
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

  public float getWeightLimitInGrams() {
    return weightLimitInGrams;
  }

  public void setWeightLimitInGrams(Float weightLimitInGrams) {
    this.weightLimitInGrams = weightLimitInGrams;
  }

}
