package com.uber.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.uber.dto.DriverRequest;
import com.uber.entity.DriverEntity;
import com.uber.service.DriverService;
import com.uber.ride.status.ApiResponse;

@RestController
@RequestMapping("/uber/v1")
public class DriverController {

	@Autowired
	private DriverService driverService;

	@PostMapping("/registerDriver")
	public ResponseEntity<ApiResponse> createDriver(@RequestBody DriverRequest driverRequest) {
		try {
			String message = driverService.registerDriver(driverRequest);
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new ApiResponse(HttpStatus.CREATED.value(), true, message));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.value(), false, "Error creating driver: " + e.getMessage()));
		}
	}

	@PutMapping("/updateDriver/{id}")
	public ResponseEntity<ApiResponse> updateDriver(@PathVariable("id") int id, @RequestBody DriverEntity driver) {
		try {
			DriverEntity updatedDriver = driverService.updateRider(id, driver);
			return ResponseEntity
					.ok(new ApiResponse(HttpStatus.OK.value(), true, "Driver updated successfully", updatedDriver));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(HttpStatus.NOT_FOUND.value(), false, e.getMessage()));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.value(), false, "Error updating driver: " + e.getMessage()));
		}
	}

	@GetMapping("/findDriverById/{id}")
	public ResponseEntity<ApiResponse> getDriverById(@PathVariable("id") int id) {
		try {
			DriverEntity driver = driverService.findById(id);
			if (driver != null) {
				return ResponseEntity
						.ok(new ApiResponse(HttpStatus.OK.value(), true, "Driver fetched successfully", driver));
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
						new ApiResponse(HttpStatus.NOT_FOUND.value(), false, "Driver with ID " + id + " not found"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.value(), false, "Error fetching driver: " + e.getMessage()));
		}
	}

	@DeleteMapping("/deleteDriver/{id}")
	public ResponseEntity<ApiResponse> deleteDriver(@PathVariable("id") int id) {
		try {
			String message = driverService.softDeleteItem(id);
			return ResponseEntity.ok(new ApiResponse(HttpStatus.OK.value(), true, message));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.value(), false, "Error deleting driver: " + e.getMessage()));
		}
	}
}
