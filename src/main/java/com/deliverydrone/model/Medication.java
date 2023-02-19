package com.deliverydrone.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "medication")
public class Medication {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", unique = true, nullable = false)
  @Pattern(regexp = "^[a-zA-Z0-9_-]+$")
  private String name;

  @Column(name = "weight_grams", nullable = false)
  private Double weightInGrams;

  @Column(name = "code", unique = true, nullable = false)
  private String code;

  @Lob
  @Column(name = "image")
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

  public Double getWeightInGrams() {
	return weightInGrams;
  }

  public void setWeightInGrams(Double weightInGrams) {
	this.weightInGrams = weightInGrams;
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
