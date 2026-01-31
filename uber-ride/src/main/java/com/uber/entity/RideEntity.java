package com.uber.entity;

import com.uber.ride.status.RideStatus;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="ride")
public class RideEntity {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name="rider_id",referencedColumnName = "id")
	private RiderEntity rider;
	
	@ManyToOne
	@JoinColumn(name="driver_id",referencedColumnName = "id")
	private DriverEntity driver;
	private String source;
	private String destination;
	private int fare;
	
	@Enumerated(EnumType.STRING)
	private RideStatus status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public RiderEntity getRider() {
		return rider;
	}

	public void setRider(RiderEntity rider) {
		this.rider = rider;
	}

	public DriverEntity getDriver() {
		return driver;
	}

	public void setDriver(DriverEntity driver) {
		this.driver = driver;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getFare() {
		return fare;
	}

	public void setFare(int fare) {
		this.fare = fare;
	}

	public RideStatus getStatus() {
		return status;
	}

	public void setStatus(RideStatus status) {
		this.status = status;
	}
	

}
