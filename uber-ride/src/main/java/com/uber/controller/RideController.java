package com.uber.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.uber.dto.RideRequest;
import com.uber.entity.RideEntity;
import com.uber.ride.dao.RideResponse;
import com.uber.ride.status.ApiResponse;
import com.uber.ride.status.RideStatus;
import com.uber.service.RideService;

@RestController
@RequestMapping("/rides/v1")
public class RideController {

	@Autowired
	private RideService rideService;

	@PostMapping("/book/{riderId}")
	public ResponseEntity<ApiResponse> bookRide(@PathVariable("riderId") int riderId,
			@RequestBody RideRequest request) {
		try {
			RideResponse ride = rideService.bookRide(riderId, request);
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new ApiResponse(HttpStatus.CREATED.value(), true, "Ride booked successfully", ride));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(HttpStatus.NOT_FOUND.value(), false, e.getMessage()));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.value(), false, "Error booking ride: " + e.getMessage()));
		}
	}

	@GetMapping("/detail/{id}")
	public ResponseEntity<ApiResponse> getRide(@PathVariable("id") int id) {
		try {
			RideEntity ride = rideService.getRideById(id);
			return ResponseEntity.ok(new ApiResponse(HttpStatus.OK.value(), true, "Ride fetched successfully", ride));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(HttpStatus.NOT_FOUND.value(), false, e.getMessage()));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.value(), false, "Error fetching ride: " + e.getMessage()));
		}
	}

	@PostMapping("/cancel/{id}")
	public ResponseEntity<ApiResponse> cancelRide(@PathVariable("id") int id) {
		try {
			RideEntity ride = rideService.cancelRide(id);
			return ResponseEntity.ok(new ApiResponse(HttpStatus.OK.value(), true, "Ride cancelled successfully", ride));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(HttpStatus.NOT_FOUND.value(), false, e.getMessage()));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.value(), false, "Error cancelling ride: " + e.getMessage()));
		}
	}

	@PutMapping("/{id}/status")
	public ResponseEntity<ApiResponse> updateRideStatus(@PathVariable("id") int id,
			@RequestParam("status") RideStatus status) {
		try {
			RideResponse updatedRide = rideService.updateRideStatus(id, status);
			return ResponseEntity
					.ok(new ApiResponse(HttpStatus.OK.value(), true, "Ride status updated successfully", updatedRide));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(HttpStatus.NOT_FOUND.value(), false, e.getMessage()));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.value(), false, "Error updating ride status: " + e.getMessage()));
		}
	}

	@GetMapping("/rider/{id}")
	public ResponseEntity<ApiResponse> getAllRidesForRider(@PathVariable("id") int id) {
		try {
			List<RideResponse> rides = rideService.getAllRidesForRider(id);
			return ResponseEntity.ok(new ApiResponse(HttpStatus.OK.value(), true, "Rides fetched successfully", rides));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(HttpStatus.NOT_FOUND.value(), false, e.getMessage()));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.value(), false, "Error fetching rides: " + e.getMessage()));
		}
	}

}
