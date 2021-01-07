package com.jointscope.poc.repository;

import com.jointscope.poc.model.Employee;
import com.jointscope.poc.model.SafeEmployee;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

 
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    // @Query("SELECT e.id, e.firstName, e.lastName, e.phone, e.email, e.userType, e.dateOfBirth, e.department, e.createdAt, e.updatedAt from Employee e")

    @Query("SELECT e from Employee e")
    List<SafeEmployee> findAllSafe();

    @Query("SELECT e from Employee e where e.id =:id ")
    List<SafeEmployee> findByIdSafe(@Param("id") Integer id);

    @Query("SELECT e from Employee e where e.department =:department ")
    List<SafeEmployee> findByDepartment(@Param("department") String department);
    // @Query("SELECT e from Employee e where e.employeeName =:name ")       // using @query
    // List<Employee> findByName(@Param("name") String name);
    // @Query(value = "SELECT e from Employee e where e.employeeName =:name ", nativeQuery = true)    @ using @query with native
    // List<Employee> findByNameNative(@Param("name") String name);
}