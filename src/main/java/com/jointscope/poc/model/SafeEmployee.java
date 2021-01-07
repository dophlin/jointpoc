package com.jointscope.poc.model;

import java.util.Date;

public interface SafeEmployee {

    public enum UserType {
        EMPLOYEE, HRADMIN;
     }
  
    Integer getId();

    UserType getUserType();

    String getFirstName();

    String getLastName();

    Date getDateOfBirth();

    String getPhone();

    String getEmail();

    String getDepartment();

    Date getCreatedAt();

    Date getUpdatedAt();
 }