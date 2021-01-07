package com.jointscope.poc.service;

import java.util.ArrayList;
import java.util.List;

import com.jointscope.poc.model.Employee;
import com.jointscope.poc.model.SafeEmployee;
import com.jointscope.poc.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// import org.slf4j.LoggerFactory;
// import org.slf4j.Logger;

@Service
public class EmployeeService {
    // private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    EmployeeRepository employeeRepository;

    public List getAll() {
        List employees = new ArrayList();
        employeeRepository.findAllSafe().forEach(employee -> employees.add(employee));
        return employees;
    }

    public List getByDepartment(String department) {
        List employees = new ArrayList();
        employeeRepository.findByDepartment(department).forEach(employee -> employees.add(employee));
        return employees;
    }

    public SafeEmployee getById(int id) {
        try {
            return employeeRepository.findByIdSafe(id).get(0);
        } catch (Exception err) {
            return null;
        }
    }

    public void saveOrUpdate(Employee employee) {
        employeeRepository.save(employee);
    }

    public void delete(int id) {
        employeeRepository.deleteById(id);
    }
}
