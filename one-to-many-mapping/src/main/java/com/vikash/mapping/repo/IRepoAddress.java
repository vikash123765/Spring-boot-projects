package com.vikash.mapping.repo;

import com.vikash.mapping.model.Address;
import com.vikash.mapping.model.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IRepoAddress extends CrudRepository<Address,Integer> {


   // Address findByAdress(List<Employee> employeeList);
}
