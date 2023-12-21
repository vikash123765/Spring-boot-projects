package com.vikash.simpleecommrese.dao;

import com.vikash.simpleecommrese.model.Category;
import com.vikash.simpleecommrese.model.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ProductFactory {

    @Bean
    public Map<Integer, Product>getEmptyProductList(){

        Map<Integer, Product> map = new HashMap<>();
        map.put(0,new Product(0,"p1",1.0, Category.CLOTHES));
        return map;
    }
}
