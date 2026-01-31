package com.uber.repositry;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.uber.entity.DriverEntity;

public interface DriverRepositry extends CrudRepository<DriverEntity, Integer> {
	List<DriverEntity> findByDeletedDriverFalse();

	Optional<DriverEntity> findFirstByAvailability(String string);

}
