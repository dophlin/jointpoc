package com.jointscope.poc.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class DayOffRequest {

   public enum Type {
      VACATION, SICKLEAVE;
   }

   public enum Status {
      REQUESTED, ACCEPTED, REJECTED;
   }

   @Id
   @GeneratedValue
   private Integer id;

   private Integer requesterHRID;
   // private Integer hrAdminHRID;

   @Enumerated(EnumType.ORDINAL)
   private Type type;
   private Date targetDate;

   @Enumerated(EnumType.ORDINAL)
   private Status status;

   private Date createdAt = new Date();
   private Date updatedAt = new Date();

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public Integer getRequesterHRID() {
      return requesterHRID;
   }

   public void setRequesterHRID(Integer requesterHRID) {
      this.requesterHRID = requesterHRID;
   }

   // public Integer getHRAdminHRID() {
   //    return hrAdminHRID;
   // }

   // public void setHRAdminHRID(Integer hrAdminHRID) {
   //    this.hrAdminHRID = hrAdminHRID;
   // }

   public Type getType() {
      return type;
   }

   public void setType(Type type) {
      this.type = type;
   }

   public Date setTargetDate() {
      return targetDate;
   }

   public void setTargetDate(Date targetDate) {
      this.targetDate = targetDate;
   }

   public Status getStatus() {
      return status;
   }

   public void setStatus(Status status) {
      this.status = status;
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
}