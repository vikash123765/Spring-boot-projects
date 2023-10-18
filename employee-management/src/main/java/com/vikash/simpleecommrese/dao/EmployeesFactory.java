package com.vikash.simpleecommrese.dao;

import com.vikash.simpleecommrese.model.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class EmployeesFactory {

    @Bean
    public Map<Integer, Employee>getEmptyEmployeeList(){

        Map<Integer, Employee> map = new HashMap<>();
        return map;
    }
}
