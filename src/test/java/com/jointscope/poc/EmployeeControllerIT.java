package com.jointscope.poc;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.jointscope.poc.model.Employee;
import com.jointscope.poc.model.SafeEmployee.UserType;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { PocApplication.class })
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerIT {

  private static final Logger logger = LoggerFactory.getLogger(EmployeeControllerIT.class);

  @LocalServerPort
  int port;

  @Before
  public void setUp() {
    RestAssured.port = port;
  }

  @Test
  public void login() {
    Map<String, String> loginBody = new HashMap<>();
    loginBody.put("username", "1");
    loginBody.put("password", "password");

    RestAssured.given().contentType(ContentType.JSON).body(loginBody).when().post("/login").then()
        .statusCode(Matchers.equalTo(200));
  }

  @Test
  public void getAllEmployees() {
    Map<String, String> loginBody = new HashMap<>();
    loginBody.put("username", "1");
    loginBody.put("password", "password");

    String token = RestAssured.given().contentType(ContentType.JSON).body(loginBody).when().post("/login").then()
        .statusCode(200).extract().header("Authorization");

    Map<String, String> getHeaders = new HashMap<>();
    getHeaders.put("Authorization", token);
    Employee[] result = RestAssured.given().contentType(ContentType.JSON).headers(getHeaders).when().get("/employee")
        .then().statusCode(200).extract().body().as(Employee[].class);

    Employee employee = new Employee();
    employee.setId(1);
    employee.setFirstName("Mohammad");
    employee.setLastName("Admin");
    employee.setUserType(UserType.HRADMIN);
    try {
      employee.setCreatedAt(new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS").parse("2021-01-07T06:45:28.964"));
      employee.setUpdatedAt(new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS").parse("2021-01-07T06:45:28.964"));
      employee.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS").parse("2015-12-17T00:00:00.000"));
    } catch (ParseException err) {
      logger.info(err.getClass().getName());
      Assert.assertTrue(false);
    }
    employee.setPhone("989126581575");
    employee.setEmail("m.khadishi@gmail.com");
    employee.setDepartment("HR");

    Assert.assertThat(result[0], Matchers.samePropertyValuesAs(employee, "createdAt", "updatedAt"));
  }


  @Test
  public void getByDepartment() {
    Map<String, String> loginBody = new HashMap<>();
    loginBody.put("username", "1");
    loginBody.put("password", "password");

    String token = RestAssured.given().contentType(ContentType.JSON).body(loginBody).when().post("/login").then()
        .statusCode(200).extract().header("Authorization");

    Map<String, String> getHeaders = new HashMap<>();
    getHeaders.put("Authorization", token);
    Employee[] result = RestAssured.given().contentType(ContentType.JSON).headers(getHeaders).when().get("/employee/department/HR")
        .then().statusCode(200).extract().body().as(Employee[].class);

    Employee employee = new Employee();
    employee.setId(1);
    employee.setFirstName("Mohammad");
    employee.setLastName("Admin");
    employee.setUserType(UserType.HRADMIN);
    try {
      employee.setCreatedAt(new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS").parse("2021-01-07T06:45:28.964"));
      employee.setUpdatedAt(new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS").parse("2021-01-07T06:45:28.964"));
      employee.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS").parse("2015-12-17T00:00:00.000"));
    } catch (ParseException err) {
      logger.info(err.getClass().getName());
      Assert.assertTrue(false);
    }
    employee.setPhone("989126581575");
    employee.setEmail("m.khadishi@gmail.com");
    employee.setDepartment("HR");

    Assert.assertThat(result[0], Matchers.samePropertyValuesAs(employee, "createdAt", "updatedAt"));
  }

  @Test
  public void registerEmployee() {
    Map<String, String> loginBody = new HashMap<>();
    loginBody.put("username", "1");
    loginBody.put("password", "password");

    String token = RestAssured.given().contentType(ContentType.JSON).body(loginBody).when().post("/login").then()
        .statusCode(200).extract().header("Authorization");

    Map<String, String> registerHeaders = new HashMap<>();
    registerHeaders.put("Authorization", token);
    Map<String, String> registerBody = new HashMap<>();
    registerBody.put("userType", "EMPLOYEE");
    registerBody.put("firstName", "Mohammad Hossein");
    registerBody.put("lastName", "Khadishi");
    registerBody.put("dateOfBirth", "2001-01-05");
    Random random = new Random();
    registerBody.put("phone", "98912" + random.nextInt(100));
    registerBody.put("email", random.nextInt(100) + "test@gmail.com");
    registerBody.put("department", "Tech");
    registerBody.put("password", "pass");

    String bodyResponse = RestAssured.given().contentType(ContentType.JSON).body(registerBody).headers(registerHeaders).when().post("/employee").then()
        .statusCode(201).extract().body().asString();

    Assert.assertThat(bodyResponse, Matchers.equalTo("Employee is registered successfully"));
  }
}
