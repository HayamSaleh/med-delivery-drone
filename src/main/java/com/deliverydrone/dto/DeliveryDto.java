package com.deliverydrone.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.deliverydrone.enms.DeliveryState;

public class DeliveryDto {

  private Long id;

  private DroneDto drone;

  private List<DeliveryMedicationDto> deliveryMedication = new ArrayList<>();

  private DeliveryState deliveryState;

  private Date startTime;

  private Date endTime;

  public DeliveryDto(Long id, DroneDto drone, DeliveryState deliveryState, Date startTime) {
    super();
    this.id = id;
    this.drone = drone;
    this.deliveryState = deliveryState;
    this.startTime = startTime;
  }

  public DeliveryDto(Long id, DroneDto drone, List<DeliveryMedicationDto> deliveryMedication,
      DeliveryState deliveryState, Date startTime) {
    this(id, drone, deliveryState, startTime);
    this.deliveryMedication = deliveryMedication;
  }

  public DeliveryDto(Long droneId) {
    this.drone = new DroneDto(droneId);
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

  public List<DeliveryMedicationDto> getDeliveryMedication() {
    return deliveryMedication;
  }

  public void setDeliveryMedication(List<DeliveryMedicationDto> deliveryMedication) {
    this.deliveryMedication = deliveryMedication;
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
