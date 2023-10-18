package com.vikash.simpleecommrese.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Employee {

   @NotNull(message = "you have to give an id ")
   private Integer id;

   @NotEmpty(message = "first name cant be empty")
    private String firstname;
   @NotBlank(message = "last name cant be blank")
    private String lastname;

    @Email(message = "please eneter a valid email")
    private String email;

    @Size(min = 8)
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@$!%*?&#])[A-Z][A-Za-z0-9@$!%*?&#]+$", message = "password is not strong enough!!!")
    private String password;
    private LocalDate userDateOfBirth;
    private Gender gender;
    private Double salary;
}
