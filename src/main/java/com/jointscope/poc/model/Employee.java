package com.jointscope.poc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@Entity
public class Employee implements SafeEmployee {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;

   @Enumerated(EnumType.ORDINAL)
   private UserType userType = UserType.EMPLOYEE;

   private String password;

   private String firstName;
   private String lastName;
   private Date dateOfBirth;

   @Column(unique = true)
   private String phone;

   @Column(unique = true)
   private String email;

   private String department;
   private Date createdAt = new Date();
   private Date updatedAt = new Date();

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   @JsonIgnore
   public String getPassword() {
      return password;
   }

   @JsonProperty
   public void setPassword(String password) {
      this.password = password;
   }

   public UserType getUserType() {
      return userType;
   }

   public void setUserType(UserType userType) {
      this.userType = userType;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public Date getDateOfBirth() {
      return dateOfBirth;
   }

   public void setDateOfBirth(Date dateOfBirth) {
      this.dateOfBirth = dateOfBirth;
   }

   public String getPhone() {
      return phone;
   }

   public void setPhone(String phone) {
      this.phone = phone;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getDepartment() {
      return department;
   }

   public void setDepartment(String department) {
      this.department = department;
   }

   public Date getCreatedAt() {
      return createdAt;
   }

   public void setCreatedAt(Date createdAt) {
      this.createdAt = createdAt;
   }

   public Date getUpdatedAt() {
      return updatedAt;
   }

   public void setUpdatedAt(Date updatedAt) {
      this.updatedAt = updatedAt;
   }

   @Override
   public String toString() {
      return this.id + ", " + this.userType + ", " + this.firstName + ", " + this.lastName + ", " + this.dateOfBirth
            + ", " + this.phone + ", " + this.email + ", " + this.department;
   }
}