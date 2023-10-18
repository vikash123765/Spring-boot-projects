package com.vikash.mapping.controller;

import com.vikash.mapping.model.Address;
import com.vikash.mapping.model.Employee;
import com.vikash.mapping.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    // post

    @PostMapping("employee")
    public String addEmployee(@RequestBody @Valid Employee emp) {
        return employeeService.addEmployee(emp);
    }

    @PostMapping("employees")
    public String addEmployees(@RequestBody @Valid List<Employee> emps) {
        return employeeService.addEmployees(emps);
    }


    //get
    @GetMapping("employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }



        // delete
        @DeleteMapping("employees")
        public String removeAllEmployees () {
            return employeeService.removeAllEmployees();
        }

        @DeleteMapping("employee/{id}")
        public String removeEmployeeById (@PathVariable Integer id){
            return employeeService.removeEmployeeById(id);
        }


        public String removeEmployeesByIds (List < Integer > ids) {
            return employeeService.deleteAllById(ids);


        }



}
