package com.uber.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uber.dto.DriverRequest;
import com.uber.entity.DriverEntity;
import com.uber.repositry.DriverRepositry;

@Service
public class DriverService {

    @Autowired
    private DriverRepositry driverRepositry;

    public String registerDriver(DriverRequest driverRequest) {
        try {
            System.out.println("DriverService.registerDriver()");

            DriverEntity entity = new DriverEntity();
            entity.setName(driverRequest.getName());
            entity.setPhone(driverRequest.getPhone());
            entity.setAvailability(driverRequest.getAvailability());
            entity.setCurrentLocation(driverRequest.getCurrentLocation());
            entity.setVechileNumber(driverRequest.getVechileNumber());

            entity = driverRepositry.save(entity);
            int driverId = entity.getId();

            if (driverId > 0) {
                return "Driver profile is created! ID: " + driverId;
            } else {
                return "Something went wrong while creating the driver profile.";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred while creating driver: " + e.getMessage();
        }
    }

    public DriverEntity updateRider(int id, DriverEntity updatedDriver) {
        try {
            DriverEntity existingDriver = driverRepositry.findById(id)
                    .orElseThrow(() -> new RuntimeException("Driver with ID " + id + " not found"));

            existingDriver.setAvailability(updatedDriver.getAvailability());
            existingDriver.setCurrentLocation(updatedDriver.getCurrentLocation());
            existingDriver.setName(updatedDriver.getName());
            existingDriver.setPhone(updatedDriver.getPhone());
            existingDriver.setVechileNumber(updatedDriver.getVechileNumber());

            return driverRepositry.save(existingDriver);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error updating driver: " + e.getMessage());
        }
    }

    public DriverEntity findById(int id) {
        try {
            System.out.println("DriverService.findById()");
            Optional<DriverEntity> item = driverRepositry.findById(id);
            return item.orElseThrow(() -> new RuntimeException("Driver with ID " + id + " not found"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching driver: " + e.getMessage());
        }
    }

    public String softDeleteItem(int id) {
        try {
            Optional<DriverEntity> item = driverRepositry.findById(id);
            if (item.isPresent()) {
                DriverEntity driver = item.get();
                driver.setDeletedDriver(true);
                driverRepositry.save(driver);
                return "Driver soft deleted successfully.";
            } else {
                return "Driver with ID " + id + " not found.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error deleting driver: " + e.getMessage();
        }
    }

}
