package com.vikash.mapping.service;

import com.vikash.mapping.model.Address;
import com.vikash.mapping.model.Employee;
import com.vikash.mapping.repo.IRepoAddress;
import com.vikash.mapping.repo.IRepoEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdressService {

    @Autowired
    IRepoAddress iRepoAddress;

    @Autowired
    IRepoEmployee iRepoEmployee;

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


    public String removeAllAdresses() {
        iRepoAddress.deleteAll();
        return "all adresses removed";
    }


    public String removeAddressById(Integer aId) {  // FOR EXAMPLE I PASS ID 3 HERE
        Address address = iRepoAddress.findById(aId).orElseThrow(); // FINING THE ADRESS LINKED TO THAT Id


        if (address.getEmployees().size() > 0)  // IF CONECTION DO EXIST(if there is an employee for this adreess)
        {
            return "can not delete need to unink first";
        }
        else {

            iRepoAddress.delete(address);
            return "address deleted";
        }
    }


    public String removeAddressesById(List<Integer> ids) {
        iRepoAddress.deleteAllById(ids);
        return "addresses was deleted";
    }

    public String LinkingAddressWithEmployees(Integer aId, List<Integer> eIds) {
        Address address = iRepoAddress.findById(aId).orElseThrow();  // se if our passed aid exist if exist store in address object
        List<Employee> employeeList = (List<Employee>) iRepoEmployee.findAllById(eIds);  // se if List of eids we pass exist if exist store in  employeeList object

        address.setEmployees(employeeList); // takes id from adress object and sets Emloyees ids from emloyelist to adress id.

        iRepoAddress.save(address);  // here we are creating the actual link between adress id and emp ids

        return "adress was lnked ";   // dimple return statemn


    }

/*    public List<Employee> getAddressById(List<Integer> eids) {
        List<Employee> employeeList = (List<Employee>) iRepoEmployee.findAllById(eids);  // finding all the employees
        Address address = iRepoAddress.findByAdress(employeeList);

        List<Employee> employeesWithAddress = new ArrayList<>();

        for (Employee employee : employeeList) {

            if(employee.getEmpId()!= null)  // IF CONECTION DO EXIST(if there is an employee for this adreess)
            {
                employee.getEmpId().equals(address.getAddressId());
                employeesWithAddress.add(employee);
            }

            }



        r
        eturn employeesWithAddress;
    }*/

    public List<Employee> getAddressById(List<Integer> eids) {
        List<Employee> employeeList = (List<Employee>) iRepoEmployee.findAllById(eids);  // finding all the employees

        return employeeList;
    }

}


