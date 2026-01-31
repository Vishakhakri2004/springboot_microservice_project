package com.uber.entity;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="driver")
public class DriverEntity {
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private int id;
	@Column
	private String name;
	@Nullable
	private long phone;
	@Nullable
	private String vechileNumber;
	@Nullable
	private String availability;
	private String currentLocation;
	@Column(nullable = false)
	private boolean deletedDriver = false;
	public boolean isDeletedDriver() {
		return deletedDriver;
	}
	public void setDeletedDriver(boolean deletedDriver) {
		this.deletedDriver = deletedDriver;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public String getVechileNumber() {
		return vechileNumber;
	}
	public void setVechileNumber(String vechileNumber) {
		this.vechileNumber = vechileNumber;
	}
	public String getAvailability() {
		return availability;
	}
	public void setAvailability(String availability) {
		this.availability = availability;
	}
	public String getCurrentLocation() {
		return currentLocation;
	}
	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}
	
	
	
	

}
