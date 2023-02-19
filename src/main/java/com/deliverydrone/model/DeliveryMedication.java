package com.deliverydrone.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "delivery_medication")
public class DeliveryMedication {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "delivery_id")
  private Delivery delivery;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "medication_id")
  private Medication medication;

  @Column(nullable = false)
  private Integer quantity;

  public Long getId() {
	return id;
  }

  public void setId(Long id) {
	this.id = id;
  }

  public Delivery getDelivery() {
	return delivery;
  }

  public void setDelivery(Delivery delivery) {
	this.delivery = delivery;
  }

  public Medication getMedication() {
	return medication;
  }

  public void setMedication(Medication medication) {
	this.medication = medication;
  }

  public Integer getQuantity() {
	return quantity;
  }

  public void setQuantity(Integer quantity) {
	this.quantity = quantity;
  }
}
