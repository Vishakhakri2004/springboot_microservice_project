package com.uber.repositry;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.uber.entity.RiderEntity;

public interface  RiderRepositry  extends CrudRepository<RiderEntity, Integer>{
	List<RiderEntity> findByDeletedFalse();

}
