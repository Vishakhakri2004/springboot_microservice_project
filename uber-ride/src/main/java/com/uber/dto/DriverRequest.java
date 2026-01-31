package com.uber.dto;

public class DriverRequest {

	private String name;
	private long phone;
	private String vechileNumber;
	private String availability;
	private String currentLocation;

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
