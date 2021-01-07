package com.jointscope.poc.controller;

import java.util.Date;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import com.jointscope.poc.model.DayOffRequest;
import com.jointscope.poc.model.SafeEmployee;
import com.jointscope.poc.model.SafeEmployee.UserType;
import com.jointscope.poc.service.DayOffRequestService;
import com.jointscope.poc.service.EmployeeService;

@RestController
public class DayOffRequestController {
   private static final Logger logger =
   LoggerFactory.getLogger(EmployeeController.class);

   @Autowired
   DayOffRequestService dayOffRequestService;

   @Autowired
   EmployeeService employeeService;

   // ----------------------------------------------------------------
   @RequestMapping(value = "/dayoff")
   public ResponseEntity<Object> getAllDayOffRequests(@RequestHeader("Authorization") String token) {
      try {
         Claims claims = Jwts.parser().setSigningKey("Mohammad@JointScope".getBytes())
               .parseClaimsJws(token.replace("Bearer", "")).getBody();
         String user = claims.getSubject();
         SafeEmployee operator = employeeService.getById(Integer.parseInt(user));
         if(operator == null || operator.getUserType() == UserType.EMPLOYEE) return new ResponseEntity<>("", HttpStatus.FORBIDDEN);

         return new ResponseEntity<>(dayOffRequestService.getAll(), HttpStatus.OK);
      } catch (Exception err) {
         return new ResponseEntity<>("There is an error", HttpStatus.INTERNAL_SERVER_ERROR);
      }
   }

   // ----------------------------------------------------------------
   @RequestMapping(value = "/dayoff", method = RequestMethod.POST)
   public ResponseEntity<Object> registerDayOffRequest(@RequestHeader("userId") Integer userId,
         @RequestBody DayOffRequest dayOffRequest) {
      try {
         SafeEmployee employee = employeeService.getById(userId);
         dayOffRequest.setRequesterHRID(userId);
         // dayOffRequest.setHRAdminHRID(null);
         dayOffRequest.setStatus(DayOffRequest.Status.REQUESTED);
         dayOffRequestService.saveOrUpdate(dayOffRequest);
         return new ResponseEntity<>("Request is registered successfully", HttpStatus.CREATED);
      } catch (Exception err) {
         return new ResponseEntity<>("There is no such employee", HttpStatus.BAD_REQUEST);
      }
   }

   // ----------------------------------------------------------------
   @RequestMapping(value = "/dayoff/{id}", method = RequestMethod.PUT)
   public ResponseEntity<Object> admitDayOffRequest(@RequestHeader("Authorization") String token, @PathVariable("id") Integer id,
         @RequestBody DayOffRequest dayOffRequest) {
      try {
         Claims claims = Jwts.parser().setSigningKey("Mohammad@JointScope".getBytes())
               .parseClaimsJws(token.replace("Bearer", "")).getBody();
         String user = claims.getSubject();
         SafeEmployee operator = employeeService.getById(Integer.parseInt(user));
         if(operator == null || operator.getUserType() == UserType.EMPLOYEE) return new ResponseEntity<>("", HttpStatus.FORBIDDEN);

         DayOffRequest request = dayOffRequestService.getById(id);
         request.setStatus(dayOffRequest.getStatus());
         request.setUpdatedAt(new Date());
         dayOffRequestService.saveOrUpdate(request);
         return new ResponseEntity<>("Request is admited successsfully", HttpStatus.OK);
      } catch (Exception err) {
         logger.info("RG" + err.getClass().getName());
         return new ResponseEntity<>("Record not found", HttpStatus.NOT_FOUND);
      }
   }
}