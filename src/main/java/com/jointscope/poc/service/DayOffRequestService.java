package com.jointscope.poc.service;

import java.util.ArrayList;
import java.util.List;

import com.jointscope.poc.model.DayOffRequest;
import com.jointscope.poc.repository.DayOffRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
@Service
public class DayOffRequestService {
 
    @Autowired
    DayOffRequestRepository dayOffRequestRepository;
 
    public List getAll() {
        List dayOffRequests = new ArrayList();
        dayOffRequestRepository.findAll().forEach(request -> dayOffRequests.add(request));
        return dayOffRequests;
    }
 
    public DayOffRequest getById(int id) {
        return dayOffRequestRepository.findById(id).get();
    }
 
    public void saveOrUpdate(DayOffRequest request) {
    	dayOffRequestRepository.save(request);
    }
 
    public void delete(int id) {
    	dayOffRequestRepository.deleteById(id);
    }
}
