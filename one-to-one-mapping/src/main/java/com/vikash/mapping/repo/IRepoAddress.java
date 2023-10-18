package com.vikash.mapping.repo;

import com.vikash.mapping.model.Address;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IRepoAddress extends CrudRepository<Address,Integer> {


}
