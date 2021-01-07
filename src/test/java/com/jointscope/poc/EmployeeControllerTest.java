package com.jointscope.poc;

import com.jointscope.poc.controller.EmployeeController;
import com.jointscope.poc.model.Employee;
import com.jointscope.poc.model.SafeEmployee;
import com.jointscope.poc.model.SafeEmployee.UserType;
import com.jointscope.poc.service.EmployeeService;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeControllerTest.class);

    @Mock
    private EmployeeService mockEmployeeService;

    @Mock
    private PasswordEncoder mockBCryptPasswordEncoder;

    @InjectMocks
    EmployeeController employeeController = new EmployeeController();

    @Test
    public void getAllEmployees() {
        // Given
        String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiZXhwIjoxNjEwODY1OTQzfQ.sFu8hSUpqWfOonpqv4erubNBoeePSulv4uaOpZ8ijjVVb_EIsQJ6GRngwanXGRvIhtiEHAz2S4a0EY1_RoH6WQ";
        Employee employee = new Employee();
        employee.setId(1);
        employee.setFirstName("Mohammad");
        employee.setLastName("Admin");
        employee.setUserType(UserType.HRADMIN);
        try {
            employee.setCreatedAt(new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS").parse("2021-01-07T06:45:28.964"));
            employee.setUpdatedAt(new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS").parse("2021-01-07T06:45:28.964"));
            employee.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS").parse("2015-12-16T20:30:00.000"));
        } catch (ParseException err) {
            logger.info(err.getClass().getName());
            Assert.assertTrue(false);
        }
        employee.setPhone("989126581575");
        employee.setEmail("m.khadishi@gmail.com");
        employee.setDepartment("HR");

        SafeEmployee safeEmployee = (SafeEmployee) employee;
        Mockito.when(this.mockEmployeeService.getById(1)).thenReturn(safeEmployee);

        List<SafeEmployee> employees = new ArrayList();
        employees.add(safeEmployee);
        Mockito.when(this.mockEmployeeService.getAll()).thenReturn(employees);

        // When
        ResponseEntity<Object> actualResponse = this.employeeController.getAllEmployees(token);
        // Then
        Assert.assertThat(actualResponse.getStatusCode(), Matchers.equalTo(HttpStatus.OK));
        Assert.assertThat(actualResponse.getBody(), Matchers.equalTo(employees));
    }

    @Test
    public void getAllEmployeesOfDepartment() {
        // Given
        String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiZXhwIjoxNjEwODY1OTQzfQ.sFu8hSUpqWfOonpqv4erubNBoeePSulv4uaOpZ8ijjVVb_EIsQJ6GRngwanXGRvIhtiEHAz2S4a0EY1_RoH6WQ";
        String department = "HR";

        Employee employee = new Employee();
        employee.setId(1);
        employee.setFirstName("Mohammad");
        employee.setLastName("Admin");
        employee.setUserType(UserType.HRADMIN);
        try {
            employee.setCreatedAt(new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS").parse("2021-01-07T06:45:28.964"));
            employee.setUpdatedAt(new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS").parse("2021-01-07T06:45:28.964"));
            employee.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS").parse("2015-12-16T20:30:00.000"));
        } catch (ParseException err) {
            logger.info(err.getClass().getName());
            Assert.assertTrue(false);
        }
        employee.setPhone("989126581575");
        employee.setEmail("m.khadishi@gmail.com");
        employee.setDepartment("HR");

        SafeEmployee safeEmployee = (SafeEmployee) employee;
        Mockito.when(this.mockEmployeeService.getById(1)).thenReturn(safeEmployee);

        List<SafeEmployee> employees = new ArrayList();
        employees.add(safeEmployee);
        Mockito.when(this.mockEmployeeService.getByDepartment(department)).thenReturn(employees);

        // When
        ResponseEntity<Object> actualResponse = this.employeeController.getAllEmployeesOfDepartment(token, department);
        // Then
        Assert.assertThat(actualResponse.getStatusCode(), Matchers.equalTo(HttpStatus.OK));
        Assert.assertThat(actualResponse.getBody(), Matchers.equalTo(employees));
    }

    @Test
    public void get() {
        // Given
        String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiZXhwIjoxNjEwODY1OTQzfQ.sFu8hSUpqWfOonpqv4erubNBoeePSulv4uaOpZ8ijjVVb_EIsQJ6GRngwanXGRvIhtiEHAz2S4a0EY1_RoH6WQ";
        Integer id = 1;

        Employee employee = new Employee();
        employee.setId(1);
        employee.setFirstName("Mohammad");
        employee.setLastName("Admin");
        employee.setUserType(UserType.HRADMIN);
        try {
            employee.setCreatedAt(new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS").parse("2021-01-07T06:45:28.964"));
            employee.setUpdatedAt(new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS").parse("2021-01-07T06:45:28.964"));
            employee.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS").parse("2015-12-16T20:30:00.000"));
        } catch (ParseException err) {
            logger.info(err.getClass().getName());
            Assert.assertTrue(false);
        }
        employee.setPhone("989126581575");
        employee.setEmail("m.khadishi@gmail.com");
        employee.setDepartment("HR");

        SafeEmployee safeEmployee = (SafeEmployee) employee;
        Mockito.when(this.mockEmployeeService.getById(1)).thenReturn(safeEmployee);

        // When
        ResponseEntity<Object> actualResponse = this.employeeController.get(token, id);
        // Then
        Assert.assertThat(actualResponse.getStatusCode(), Matchers.equalTo(HttpStatus.OK));
        Assert.assertThat(actualResponse.getBody(), Matchers.equalTo(safeEmployee));
    }

    @Test
    public void registerEmployee() {
        // Given
        String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiZXhwIjoxNjEwODY1OTQzfQ.sFu8hSUpqWfOonpqv4erubNBoeePSulv4uaOpZ8ijjVVb_EIsQJ6GRngwanXGRvIhtiEHAz2S4a0EY1_RoH6WQ";
        Employee sampleBody = new Employee();
        sampleBody.setFirstName("Omid");
        sampleBody.setLastName("Yarmohammadi");
        sampleBody.setUserType(UserType.EMPLOYEE);
        try {
            sampleBody.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse("2001-01-01"));
        } catch (ParseException err) {
            logger.info(err.getClass().getName());
            Assert.assertTrue(false);
        }
        sampleBody.setPhone("989122187098");
        sampleBody.setEmail("sample@sample.com");
        sampleBody.setDepartment("Tech");
        sampleBody.setPassword("pass");

        Employee employee = new Employee();
        employee.setId(1);
        employee.setFirstName("Mohammad");
        employee.setLastName("Admin");
        employee.setUserType(UserType.HRADMIN);
        try {
            employee.setCreatedAt(new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS").parse("2021-01-07T06:45:28.964"));
            employee.setUpdatedAt(new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS").parse("2021-01-07T06:45:28.964"));
            employee.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS").parse("2015-12-16T20:30:00.000"));
        } catch (ParseException err) {
            logger.info(err.getClass().getName());
            Assert.assertTrue(false);
        }
        employee.setPhone("989126581575");
        employee.setEmail("m.khadishi@gmail.com");
        employee.setDepartment("HR");

        SafeEmployee safeEmployee = (SafeEmployee) employee;
        Mockito.when(this.mockEmployeeService.getById(1)).thenReturn(safeEmployee);
        Mockito.when(this.mockBCryptPasswordEncoder.encode(sampleBody.getPassword())).thenReturn("$2y$12$cmUjoedIV5h3NkNVWjPxBeXs9Src37enhR6GbANo4rIpVPQOiPRKe");

        // When
        ResponseEntity<Object> actualResponse = this.employeeController.registerEmployee(token, sampleBody);
        logger.info(actualResponse.getStatusCode() + "");
        // Then
        Assert.assertThat(actualResponse.getStatusCode(), Matchers.equalTo(HttpStatus.CREATED));
        Assert.assertThat(actualResponse.getBody(), Matchers.equalTo("Employee is registered successfully"));
    }

    @Test
    public void updateEmployee() {
        // Given
        String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiZXhwIjoxNjEwODY1OTQzfQ.sFu8hSUpqWfOonpqv4erubNBoeePSulv4uaOpZ8ijjVVb_EIsQJ6GRngwanXGRvIhtiEHAz2S4a0EY1_RoH6WQ";
        Integer id = 2;
        Employee sampleBody = new Employee();
        sampleBody.setFirstName("Omid");
        sampleBody.setLastName("Yarmohammadi");
        sampleBody.setUserType(UserType.EMPLOYEE);
        try {
            sampleBody.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse("2001-01-01"));
        } catch (ParseException err) {
            logger.info(err.getClass().getName());
            Assert.assertTrue(false);
        }
        sampleBody.setPhone("989122187098");
        sampleBody.setEmail("sample@sample.com");
        sampleBody.setDepartment("Tech");
        sampleBody.setPassword("pass");

        Employee employee = new Employee();
        employee.setId(1);
        employee.setFirstName("Mohammad");
        employee.setLastName("Admin");
        employee.setUserType(UserType.HRADMIN);
        try {
            employee.setCreatedAt(new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS").parse("2021-01-07T06:45:28.964"));
            employee.setUpdatedAt(new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS").parse("2021-01-07T06:45:28.964"));
            employee.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS").parse("2015-12-16T20:30:00.000"));
        } catch (ParseException err) {
            logger.info(err.getClass().getName());
            Assert.assertTrue(false);
        }
        employee.setPhone("989126581575");
        employee.setEmail("m.khadishi@gmail.com");
        employee.setDepartment("HR");

        SafeEmployee safeEmployee = (SafeEmployee) employee;
        Mockito.when(this.mockEmployeeService.getById(1)).thenReturn(safeEmployee);

        // When
        ResponseEntity<Object> actualResponse = this.employeeController.updateEmployee(token, id, sampleBody);
        logger.info(actualResponse.getStatusCode() + "");
        // Then
        Assert.assertThat(actualResponse.getStatusCode(), Matchers.equalTo(HttpStatus.OK));
        Assert.assertThat(actualResponse.getBody(), Matchers.equalTo("Employee is updated successsfully"));
    }

    @Test
    public void delete() {
        // Given
        String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiZXhwIjoxNjEwODY1OTQzfQ.sFu8hSUpqWfOonpqv4erubNBoeePSulv4uaOpZ8ijjVVb_EIsQJ6GRngwanXGRvIhtiEHAz2S4a0EY1_RoH6WQ";
        Integer id = 2;
        Employee sampleBody = new Employee();
        sampleBody.setFirstName("Omid");
        sampleBody.setLastName("Yarmohammadi");
        sampleBody.setUserType(UserType.EMPLOYEE);
        try {
            sampleBody.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse("2001-01-01"));
        } catch (ParseException err) {
            logger.info(err.getClass().getName());
            Assert.assertTrue(false);
        }
        sampleBody.setPhone("989122187098");
        sampleBody.setEmail("sample@sample.com");
        sampleBody.setDepartment("Tech");
        sampleBody.setPassword("$2y$12$cmUjoedIV5h3NkNVWjPxBeXs9Src37enhR6GbANo4rIpVPQOiPRKe");

        Employee employee = new Employee();
        employee.setId(1);
        employee.setFirstName("Mohammad");
        employee.setLastName("Admin");
        employee.setUserType(UserType.HRADMIN);
        try {
            employee.setCreatedAt(new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS").parse("2021-01-07T06:45:28.964"));
            employee.setUpdatedAt(new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS").parse("2021-01-07T06:45:28.964"));
            employee.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS").parse("2015-12-16T20:30:00.000"));
        } catch (ParseException err) {
            logger.info(err.getClass().getName());
            Assert.assertTrue(false);
        }
        employee.setPhone("989126581575");
        employee.setEmail("m.khadishi@gmail.com");
        employee.setDepartment("HR");

        SafeEmployee safeEmployee = (SafeEmployee) employee;
        Mockito.when(this.mockEmployeeService.getById(1)).thenReturn(safeEmployee);
        Mockito.when(this.mockEmployeeService.getById(2)).thenReturn(sampleBody);

        // When
        ResponseEntity<Object> actualResponse = this.employeeController.delete(token, id);
        logger.info(actualResponse.getStatusCode() + "");
        // Then
        Assert.assertThat(actualResponse.getStatusCode(), Matchers.equalTo(HttpStatus.OK));
        Assert.assertThat(actualResponse.getBody(), Matchers.equalTo("Employee is deleted successsfully"));
    }

}
