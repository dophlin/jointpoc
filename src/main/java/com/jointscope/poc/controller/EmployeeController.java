package com.jointscope.poc.controller;

// import org.slf4j.LoggerFactory;
// import org.slf4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.jointscope.poc.model.Employee;
import com.jointscope.poc.model.SafeEmployee;
import com.jointscope.poc.model.SafeEmployee.UserType;
import com.jointscope.poc.service.EmployeeService;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@RestController
public class EmployeeController {
   private static final Logger logger =
   LoggerFactory.getLogger(EmployeeController.class);

   @Autowired
   EmployeeService employeeService;

   @Autowired
   PasswordEncoder bCryptPasswordEncoder;

   // ----------------------------------------------------------------
   @RequestMapping(value = "/employee")
   public ResponseEntity<Object> getAllEmployees(@RequestHeader("Authorization") String token) {
      try {
         Claims claims = Jwts.parser().setSigningKey("Mohammad@JointScope".getBytes())
               .parseClaimsJws(token.replace("Bearer", "")).getBody();
         String user = claims.getSubject();
         SafeEmployee operator = employeeService.getById(Integer.parseInt(user));
         if(operator == null || operator.getUserType() == UserType.EMPLOYEE) return new ResponseEntity<>("", HttpStatus.FORBIDDEN);

         return new ResponseEntity<>(employeeService.getAll(), HttpStatus.OK);
      } catch (Exception err) {
         logger.info("2502:" + err.getClass().getName());
         return new ResponseEntity<>("There is an error", HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }

   // ----------------------------------------------------------------
   @RequestMapping(value = "/employee/department/{department}")
   public ResponseEntity<Object> getAllEmployeesOfDepartment(@RequestHeader("Authorization") String token, @PathVariable("department") String department) {
      try {
         Claims claims = Jwts.parser().setSigningKey("Mohammad@JointScope".getBytes())
               .parseClaimsJws(token.replace("Bearer", "")).getBody();
         String user = claims.getSubject();
         SafeEmployee operator = employeeService.getById(Integer.parseInt(user));
         if(operator == null || operator.getUserType() == UserType.EMPLOYEE) return new ResponseEntity<>("", HttpStatus.FORBIDDEN);

         return new ResponseEntity<>(employeeService.getByDepartment(department), HttpStatus.OK);
      } catch (Exception err) {
         return new ResponseEntity<>("There is an error", HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }

   // ----------------------------------------------------------------
   @RequestMapping(value = "/employee/{id}")
   public ResponseEntity<Object> get(@RequestHeader("Authorization") String token, @PathVariable("id") Integer id) {
      try {
         Claims claims = Jwts.parser().setSigningKey("Mohammad@JointScope".getBytes())
               .parseClaimsJws(token.replace("Bearer", "")).getBody();
         String user = claims.getSubject();
         SafeEmployee operator = employeeService.getById(Integer.parseInt(user));
         if(operator == null || operator.getUserType() == UserType.EMPLOYEE) return new ResponseEntity<>("", HttpStatus.FORBIDDEN);

         return new ResponseEntity<>(employeeService.getById(id), HttpStatus.OK);
      } catch (Exception err) {
         return new ResponseEntity<>("Record not found", HttpStatus.NOT_FOUND);
      }
   }

   // ----------------------------------------------------------------
   @RequestMapping(value = "/employee", method = RequestMethod.POST)
   public ResponseEntity<Object> registerEmployee(@RequestHeader("Authorization") String token, @RequestBody Employee employee) {
      try {
         Claims claims = Jwts.parser().setSigningKey("Mohammad@JointScope".getBytes())
               .parseClaimsJws(token.replace("Bearer", "")).getBody();
         String user = claims.getSubject();
         SafeEmployee operator = employeeService.getById(Integer.parseInt(user));
         if(operator == null || operator.getUserType() == UserType.EMPLOYEE) return new ResponseEntity<>("", HttpStatus.FORBIDDEN);

         employee.setPassword(bCryptPasswordEncoder.encode(employee.getPassword()));

         logger.info("3000:" + employee.toString());
         employeeService.saveOrUpdate(employee);
         return new ResponseEntity<>("Employee is registered successfully", HttpStatus.CREATED);
      } catch (org.springframework.dao.DataIntegrityViolationException err) {
         logger.info("***:" + err.getMessage());
         return new ResponseEntity<>("Record with this sort of data already exists", HttpStatus.CONFLICT);
      } catch (Exception err) {
         return new ResponseEntity<>("There is an error", HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }

   // ----------------------------------------------------------------
   @RequestMapping(value = "/employee/{id}", method = RequestMethod.PUT)
   public ResponseEntity<Object> updateEmployee(@RequestHeader("Authorization") String token, @PathVariable("id") Integer id, @RequestBody Employee employee) {
      try {
         Claims claims = Jwts.parser().setSigningKey("Mohammad@JointScope".getBytes())
               .parseClaimsJws(token.replace("Bearer", "")).getBody();
         String user = claims.getSubject();
         SafeEmployee operator = employeeService.getById(Integer.parseInt(user));
         if(operator == null || operator.getUserType() == UserType.EMPLOYEE) return new ResponseEntity<>("", HttpStatus.FORBIDDEN);

         SafeEmployee orgEmployee = employeeService.getById(id);
         employee.setId(id);
         employeeService.saveOrUpdate(employee);
         return new ResponseEntity<>("Employee is updated successsfully", HttpStatus.OK);
      } catch (Exception err) {
         return new ResponseEntity<>("Record not found", HttpStatus.NOT_FOUND);
      }
   }

   // ----------------------------------------------------------------
   @RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE)
   public ResponseEntity<Object> delete(@RequestHeader("Authorization") String token, @PathVariable("id") Integer id) {
      try {
         Claims claims = Jwts.parser().setSigningKey("Mohammad@JointScope".getBytes())
               .parseClaimsJws(token.replace("Bearer", "")).getBody();
         String user = claims.getSubject();
         SafeEmployee operator = employeeService.getById(Integer.parseInt(user));
         if(operator == null || operator.getUserType() == UserType.EMPLOYEE) return new ResponseEntity<>("", HttpStatus.FORBIDDEN);

         SafeEmployee orgEmployee = employeeService.getById(id);
         employeeService.delete(id);
         return new ResponseEntity<>("Employee is deleted successsfully", HttpStatus.OK);
      } catch (Exception err) {
         return new ResponseEntity<>("Record not found", HttpStatus.NOT_FOUND);
      }
   }
}