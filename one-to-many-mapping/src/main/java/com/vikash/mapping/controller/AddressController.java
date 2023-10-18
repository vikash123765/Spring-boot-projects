package com.vikash.mapping.controller;

import com.vikash.mapping.model.Address;
import com.vikash.mapping.model.Employee;
import com.vikash.mapping.service.AdressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Validated
@RestController
public class AddressController {

    @Autowired
    AdressService adressService;


    // post

    @PostMapping("address")
    public String addAddress(@RequestBody @Valid  Address address) {
        return adressService.addAddress(address);
    }

    @PostMapping("addresses")
    public String addAddresses(@RequestBody  @Valid  List<Address> addresses) {
        return adressService.addAddresses(addresses);
    }

    //get
    @GetMapping("addresses")
    public Iterable<Address> getAllAddresses() {
        return adressService.getAllAddresses();
    }

    @GetMapping("address/ids")
    public List<Employee> getAddressById(@RequestBody List<Integer> eids){
        return adressService.getAddressById(eids);
    }

    // put

    @PutMapping("addresses/aId{aId}/eIds{eIds}")
    public String LinkingAddressWithEmployees(@PathVariable Integer aId, @RequestBody List<Integer> eIds){
        return adressService.LinkingAddressWithEmployees(aId,eIds);

    }
    //delete

    @DeleteMapping("addresses")
    public String removeAllEmployees() {
        return adressService.removeAllAdresses();
    }



  @DeleteMapping("address/{id}")
    public String removeAddressById(@PathVariable Integer id) {
        return adressService.removeAddressById(id);
    }




    @DeleteMapping("addresses/ids")
      public String removeAddressesByIds(@RequestBody List<Integer> ids) {
          return adressService.removeAddressesById(ids);

      }

}
