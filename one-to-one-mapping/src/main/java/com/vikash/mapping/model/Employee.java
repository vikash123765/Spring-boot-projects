package com.vikash.mapping.model;

import com.vikash.mapping.model.Address;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer empId;

    @NotBlank(message = "can not be blank")
    private String empName;


    @ManyToOne
    @JoinColumn(name = "fk_address_id")
    Address address;


}
