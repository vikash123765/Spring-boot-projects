package com.vikash.simpleecommrese.dao;

import com.vikash.simpleecommrese.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;
@Repository
public class EmployeesRepo {

    @Autowired
   private Map<Integer, Employee> employeeList;

    public Map<Integer, Employee> getEmployees() {

     return employeeList;
    }
}
