package com.vikash.simpleecommrese.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {

    @NotNull()
    private Integer id;
    private String name;
    private double price;
    private Category Category;

}
