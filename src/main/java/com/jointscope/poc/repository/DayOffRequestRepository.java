package com.jointscope.poc.repository;

import com.jointscope.poc.model.DayOffRequest;
import org.springframework.data.repository.CrudRepository;
 
public interface DayOffRequestRepository extends CrudRepository<DayOffRequest, Integer> {}