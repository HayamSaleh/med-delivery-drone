package com.deliverydrone.dto;

public class DeliveryMedicationDto {

  private Long id;
  private MedicationDto medication;
  private Integer quantity;

  public Long getId() {
    return id;
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
