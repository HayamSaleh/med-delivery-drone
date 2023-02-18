package com.deliverydrone.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "drone")
public class Drone {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "serial_number", length = 100, nullable = false)
  private String serialNumber;

  @ManyToOne
  @JoinColumn(name = "model_id", referencedColumnName = "id", nullable = false)
  private DroneModel model;

  @Column(name = "battery_current_capacity", nullable = false)
  private Double batteryCurrentCapacity;

  @ManyToOne
  @JoinColumn(name = "current_state_id", referencedColumnName = "id", nullable = false)
  private DroneState currentState;

  public Drone() {
    super();
  }

  public Drone(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getSerialNumber() {
    return serialNumber;
  }

  public void setSerialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  public DroneModel getModel() {
    return model;
  }

  public void setModel(DroneModel model) {
    this.model = model;
  }

  public Double getBatteryCurrentCapacity() {
    return batteryCurrentCapacity;
  }

  public void setBatteryCurrentCapacity(Double batteryCurrentCapacity) {
    this.batteryCurrentCapacity = batteryCurrentCapacity;
  }

  public DroneState getCurrentState() {
    return currentState;
  }

  public void setCurrentState(DroneState currentState) {
    this.currentState = currentState;
  }

}
