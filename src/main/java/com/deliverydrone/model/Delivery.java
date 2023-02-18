package com.deliverydrone.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.deliverydrone.enms.DeliveryState;

@Entity
@Table(name = "delivery")
public class Delivery {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "drone_id", nullable = false)
  private Drone drone;

  @OneToMany(mappedBy = "delivery", cascade = CascadeType.PERSIST)
  // @JoinTable(name = "delivery_medication", joinColumns = @JoinColumn(name = "delivery_id"),
  // inverseJoinColumns = @JoinColumn(name = "id"))
  private List<DeliveryMedication> deliveryMedication = new ArrayList<>();

  @Enumerated(EnumType.STRING)
  @Column(name = "delivery_state", nullable = false)
  private DeliveryState deliveryState;

  @Column(name = "start_time", nullable = false)
  private Date startTime;

  @Column(name = "end_time")
  private Date endTime;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Drone getDrone() {
    return drone;
  }

  public void setDrone(Drone drone) {
    this.drone = drone;
  }

  public List<DeliveryMedication> getDeliveryMedication() {
    return deliveryMedication;
  }

  public void setDeliveryMedication(List<DeliveryMedication> deliveryMedication) {
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
