package com.uber.repositry;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.uber.entity.RideEntity;

public interface RideRepositry extends CrudRepository<RideEntity, Integer> {
	List<RideEntity> findByRiderId(int riderId);

}
