package com.vikash.mapping.service;

import com.vikash.mapping.model.Address;
import com.vikash.mapping.model.Employee;
import com.vikash.mapping.repo.IRepoAddress;
import com.vikash.mapping.repo.IRepoEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class EmployeeService {


    @Autowired
    IRepoEmployee iRepoEmployee;


    @Autowired
    AdressService adressService;

    @Autowired
    IRepoAddress iRepoAddress;



    //post

    public String addEmployee(Employee emp) {
        //emp.setAddress(null);
        iRepoEmployee.save(emp);
        return "employee added";
    }

    public String addEmployees(List<Employee> emps) {
        iRepoEmployee.saveAll(emps);
        return "employees added";
    }


    public List<Employee> getAllEmployees() {
        return (List<Employee>) iRepoEmployee.findAll();
    }




    public String removeAllEmployees() {

        iRepoEmployee.deleteAll();
        return "all employees where deleted";
    }

    public String removeEmployeeById(Integer id) {
        iRepoEmployee.deleteById(id);
        return "stock was removed";
    }

    public String deleteAllById(List<Integer> ids) {
        iRepoEmployee.deleteAllById(ids);
       return "employees with given ids where removed";

    }
}


