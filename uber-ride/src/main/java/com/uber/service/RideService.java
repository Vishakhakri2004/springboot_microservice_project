package com.uber.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uber.dto.RideRequest;
import com.uber.entity.DriverEntity;
import com.uber.entity.RideEntity;
import com.uber.entity.RiderEntity;
import com.uber.repositry.DriverRepositry;
import com.uber.repositry.RideRepositry;
import com.uber.repositry.RiderRepositry;
import com.uber.ride.dao.RideResponse;
import com.uber.ride.status.RideStatus;

@Service
@Transactional
public class RideService {

    @Autowired
    private RideRepositry rideRepo;

    @Autowired
    private DriverRepositry driverRepo;

    @Autowired
    private RiderRepositry riderRepo;

    // 1️⃣ Book a new ride
    public RideResponse bookRide(int riderId, RideRequest request) {
        try {
            // Validate rider exists
            RiderEntity rider = riderRepo.findById(riderId)
                    .orElseThrow(() -> new RuntimeException("Rider with ID " + riderId + " not found"));

            // Find first available driver
            DriverEntity driver = driverRepo.findFirstByAvailability("AVAILABLE")
                    .orElseThrow(() -> new RuntimeException("No drivers available"));

            // Create ride
            RideEntity ride = new RideEntity();
            ride.setRider(rider);
            ride.setDriver(driver);
            ride.setSource(request.getSource());
            ride.setDestination(request.getDestination());
            ride.setFare(calculateFare(request.getSource(), request.getDestination()));
            ride.setStatus(RideStatus.REQUESTED);

            // Update driver availability to BUSY
            driver.setAvailability("BUSY");
            driverRepo.save(driver);

            // Save ride
            RideEntity savedRide = rideRepo.save(ride);
            return toDTO(savedRide);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error booking ride: " + e.getMessage());
        }
    }

    // Dummy fare calculation
    private int calculateFare(String source, String destination) {
        return 300; // simple fixed fare
    }

    // Fetch ride by ID
    public RideEntity getRideById(int id) {
        try {
            return rideRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("Ride with ID " + id + " not found"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching ride: " + e.getMessage());
        }
    }

    // Cancel ride
    public RideEntity cancelRide(int rideId) {
        try {
            RideEntity ride = getRideById(rideId);
            ride.setStatus(RideStatus.CANCELLED);

            // Set driver available again
            DriverEntity driver = ride.getDriver();
            driver.setAvailability("AVAILABLE");
            driverRepo.save(driver);

            return rideRepo.save(ride);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error cancelling ride: " + e.getMessage());
        }
    }

    private RideResponse toDTO(RideEntity ride) {
        RideResponse dto = new RideResponse();
        dto.setRideId(ride.getId());
        dto.setDriverName(ride.getDriver().getName());
        dto.setVehicleNumber(ride.getDriver().getVechileNumber());
        dto.setDriverPhone(ride.getDriver().getPhone());
        dto.setSource(ride.getSource());
        dto.setDestination(ride.getDestination());
        dto.setFare(ride.getFare());
        dto.setStatus(ride.getStatus().toString());
        return dto;
    }

    public RideResponse updateRideStatus(int id, RideStatus status) {
        try {
            RideEntity ride = rideRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("Ride with ID " + id + " not found"));

            ride.setStatus(status);

            // If ride completed or cancelled, make driver AVAILABLE again
            if (status == RideStatus.COMPLETED || status == RideStatus.CANCELLED) {
                DriverEntity driver = ride.getDriver();
                driver.setAvailability("AVAILABLE");
                driverRepo.save(driver);
            }

            RideEntity updatedRide = rideRepo.save(ride);
            return toDTO(updatedRide);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error updating ride status: " + e.getMessage());
        }
    }

    public List<RideResponse> getAllRidesForRider(int id) {
        try {
            List<RideEntity> rides = rideRepo.findByRiderId(id);
            return rides.stream().map(this::toDTO).toList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching rides for rider: " + e.getMessage());
        }
    }

}
