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

    @ManyToMany
    @JoinTable(name = "fk_join_table",joinColumns = @JoinColumn(name ="fk_add_id" ),inverseJoinColumns =@JoinColumn(name ="fk_emp_id" ) )
    List<Employee> employees;
}
