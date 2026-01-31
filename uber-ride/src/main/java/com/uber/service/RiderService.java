package com.uber.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uber.dto.RiderRequest;
import com.uber.entity.RiderEntity;
import com.uber.repositry.RiderRepositry;

@Service
public class RiderService {

    @Autowired
    private RiderRepositry riderRepositry;

    public String createRider(RiderRequest riderRequest) {
        try {
            System.out.println("RiderService.createRider()");

            RiderEntity entity = new RiderEntity();
            entity.setName(riderRequest.getName());
            entity.setPhone(riderRequest.getPhone());
            entity.setEmail(riderRequest.getEmail());
            entity.setCurrentLocation(riderRequest.getCurrentLocation());

            entity = riderRepositry.save(entity);
            int riderId = entity.getId();

            if (riderId > 0) {
                return "Rider profile is created! ID: " + riderId;
            } else {
                return "Something went wrong while creating the rider profile.";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred while creating rider: " + e.getMessage();
        }
    }

    public RiderEntity findById(int id) {
        try {
            System.out.println("RiderService.findById()");
            Optional<RiderEntity> item = riderRepositry.findById(id);
            return item.orElseThrow(() -> new RuntimeException("Rider with ID " + id + " not found"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching rider: " + e.getMessage());
        }
    }

    public RiderEntity updateRider(int id, RiderEntity updatedRider) {
        try {
            RiderEntity existingRider = riderRepositry.findById(id)
                    .orElseThrow(() -> new RuntimeException("Rider with ID " + id + " not found"));

            existingRider.setName(updatedRider.getName());
            existingRider.setEmail(updatedRider.getEmail());
            existingRider.setPhone(updatedRider.getPhone());
            existingRider.setCurrentLocation(updatedRider.getCurrentLocation());

            return riderRepositry.save(existingRider);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error updating rider: " + e.getMessage());
        }
    }

    public String softDeleteItem(int id) {
        try {
            Optional<RiderEntity> item = riderRepositry.findById(id);
            if (item.isPresent()) {
                RiderEntity rider = item.get();
                rider.setDeleted(true);
                riderRepositry.save(rider);
                return "Rider soft deleted successfully.";
            } else {
                return "Rider with ID " + id + " not found.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error deleting rider: " + e.getMessage();
        }
    }
}
