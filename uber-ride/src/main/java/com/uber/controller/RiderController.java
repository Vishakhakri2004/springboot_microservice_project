package com.uber.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.uber.dto.RiderRequest;
import com.uber.entity.RiderEntity;
import com.uber.ride.status.ApiResponse;
import com.uber.service.RiderService;

@RestController
@RequestMapping("/uber/v1")
public class RiderController {

	@Autowired
	private RiderService riderService;


	@PostMapping("/createRider")
	public ResponseEntity<ApiResponse> createRider(@RequestBody RiderRequest riderRequest) {
		try {
			String message = riderService.createRider(riderRequest);
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new ApiResponse(HttpStatus.CREATED.value(), true, message));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.value(), false, "Error creating rider: " + e.getMessage()));
		}
	}


	@GetMapping("/findById/{id}")
	public ResponseEntity<ApiResponse> getRiderById(@PathVariable("id") int id) {
		try {
			RiderEntity rider = riderService.findById(id);
			if (rider != null) {
				return ResponseEntity
						.ok(new ApiResponse(HttpStatus.OK.value(), true, "Rider fetched successfully", rider));
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
						new ApiResponse(HttpStatus.NOT_FOUND.value(), false, "Rider with ID " + id + " not found"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.value(), false, "Error fetching rider: " + e.getMessage()));
		}
	}

	@PutMapping("/updateRider/{id}")
	public ResponseEntity<ApiResponse> updateRider(@PathVariable("id") int id, @RequestBody RiderEntity rider) {
		try {
			RiderEntity updatedRider = riderService.updateRider(id, rider);
			return ResponseEntity
					.ok(new ApiResponse(HttpStatus.OK.value(), true, "Rider updated successfully", updatedRider));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse(HttpStatus.NOT_FOUND.value(), false, e.getMessage()));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.value(), false, "Error updating rider: " + e.getMessage()));
		}
	}

	@DeleteMapping("/deleteRider/{id}")
	public ResponseEntity<ApiResponse> deleteRider(@PathVariable("id") int id) {
		try {
			String message = riderService.softDeleteItem(id);
			return ResponseEntity.ok(new ApiResponse(HttpStatus.OK.value(), true, message));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(
					HttpStatus.INTERNAL_SERVER_ERROR.value(), false, "Error deleting rider: " + e.getMessage()));
		}
	}
}
