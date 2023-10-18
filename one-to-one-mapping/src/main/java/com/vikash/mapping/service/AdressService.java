package com.vikash.mapping.service;

import com.vikash.mapping.model.Address;
import com.vikash.mapping.model.Employee;
import com.vikash.mapping.repo.IRepoAddress;
import com.vikash.mapping.repo.IRepoEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdressService {

    @Autowired
    IRepoAddress iRepoAddress;

    @Autowired
    IRepoEmployee  iRepoEmployee;

    public String addAddress(Address address) {
       iRepoAddress.save(address);
        return "added address";
    }

    public String addAddresses(List<Address> addresses) {
        iRepoAddress.saveAll(addresses);
        return "added addresses";
    }

    public Iterable<Address> getAllAddresses() {
        return iRepoAddress.findAll();
    }

    public Address getAdressById(Integer aid2) {
        return iRepoAddress.findById(aid2).orElseThrow();
    }

    public String removeAllAdresses() {
        iRepoAddress.deleteAll();
        return "all adresses removed";
    }

    public String removeAddressById(Integer aId) {  // FOR EXAMPLE I PASS ID 3 HERE
        Address address = iRepoAddress.findById(aId).orElseThrow(); // FINING THE ADRESS LINKED TO THAT aId
        Employee emp = iRepoEmployee.findFirstByAddress(address);  //using employee repo is there any emloyee who is linked withthis adress, we use first the get one employee(find by the adrees field of employee)

        if(emp!= null)  // IF CONECTION DO EXIST(if there is an employee for this adreess)
        {
            return emp.getEmpName() + " linked with address id" + aId; // REURN THIS
        }

        iRepoAddress.deleteById(aId); // ELSE REURN THIS
        return "address deleted";

    }


    public String removeAddressesById(List<Integer> ids) {
        iRepoAddress.deleteAllById(ids);
        return  "addresses was deleted";
    }
}



