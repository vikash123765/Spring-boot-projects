package com.vikash.mapping.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer addressId;

   @NotEmpty(message = "can not be empty")
   @Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+",message = "adress not entered correctly")
   private String addressName;

    @OneToMany
    @JoinColumn(name = "fk_address_id")
    List<Employee> employees;
}
