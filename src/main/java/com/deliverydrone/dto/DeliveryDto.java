package com.deliverydrone.dto;

import com.deliverydrone.model.DeliveryState;

public class DeliveryDto {
  private Long id;
  private Long medicationId;
  private DeliveryState deliveryState;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getMedicationId() {
    return medicationId;
  }

  public void setMedicationId(Long medicationId) {
    this.medicationId = medicationId;
  }

  public DeliveryState getDeliveryState() {
    return deliveryState;
  }

  public void setDeliveryState(DeliveryState deliveryState) {
    this.deliveryState = deliveryState;
  }

}
