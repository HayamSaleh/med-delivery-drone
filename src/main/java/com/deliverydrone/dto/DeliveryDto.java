package com.deliverydrone.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.deliverydrone.enums.DeliveryState;

public class DeliveryDto {

  private Long id;
  private DroneDto drone;
  private List<DeliveryMedicationDto> deliveryMedications = new ArrayList<>();
  private DeliveryState deliveryState;
  private Date startTime;
  private Date endTime;

  public DeliveryDto() {
	super();
  }

  public DeliveryDto(DroneDto drone, DeliveryState deliveryState, Date startTime) {
	super();
	this.drone = drone;
	this.deliveryState = deliveryState;
	this.startTime = startTime;
  }

  public Long getId() {
	return id;
  }

  public void setId(Long id) {
	this.id = id;
  }

  public DroneDto getDrone() {
	return drone;
  }

  public void setDrone(DroneDto drone) {
	this.drone = drone;
  }

  public List<DeliveryMedicationDto> getDeliveryMedications() {
	return deliveryMedications;
  }

  public void setDeliveryMedications(List<DeliveryMedicationDto> deliveryMedications) {
	this.deliveryMedications = deliveryMedications;
  }

  public DeliveryState getDeliveryState() {
	return deliveryState;
  }

  public void setDeliveryState(DeliveryState deliveryState) {
	this.deliveryState = deliveryState;
  }

  public Date getStartTime() {
	return startTime;
  }

  public void setStartTime(Date startTime) {
	this.startTime = startTime;
  }

  public Date getEndTime() {
	return endTime;
  }

  public void setEndTime(Date endTime) {
	this.endTime = endTime;
  }

}
