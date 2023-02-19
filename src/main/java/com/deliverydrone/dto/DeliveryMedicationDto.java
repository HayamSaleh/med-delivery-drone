package com.deliverydrone.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class DeliveryMedicationDto {

  private Long id;
  @JsonIgnore
  private DeliveryDto delivery;
  private MedicationDto medication;
  private Integer quantity;

  public Long getId() {
	return id;
  }

  public DeliveryDto getDelivery() {
	return delivery;
  }

  public void setDelivery(DeliveryDto delivery) {
	this.delivery = delivery;
  }

  public void setId(Long id) {
	this.id = id;
  }

  public MedicationDto getMedication() {
	return medication;
  }

  public void setMedication(MedicationDto medication) {
	this.medication = medication;
  }

  public Integer getQuantity() {
	return quantity;
  }

  public void setQuantity(Integer quantity) {
	this.quantity = quantity;
  }
}
