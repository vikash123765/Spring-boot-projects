package com.vikash.mapping.repo;

import com.vikash.mapping.model.Address;
import com.vikash.mapping.model.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IRepoEmployee extends CrudRepository<Employee,Integer> {
   // List<Employee> findByAddress(Address address);

    //Employee findFirstByAddress(Address address);
}
