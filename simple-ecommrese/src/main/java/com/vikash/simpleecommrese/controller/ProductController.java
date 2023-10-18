package com.vikash.simpleecommrese.controller;

import com.vikash.simpleecommrese.model.Category;
import com.vikash.simpleecommrese.model.IncreasOrDeacrease;
import com.vikash.simpleecommrese.model.Product;
import com.vikash.simpleecommrese.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Validated
@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    //GET

    // get all products
    @GetMapping("products")
    public Map<Integer, Product> getAllProducts(){
        return productService.getAllProducts();
    }

    // get product by id
    @GetMapping("products/id/{id}")
    public  Product getProductById(@PathVariable Integer id){
        return productService.getProductById(id);
    }

    // get products in a particular price range
    @GetMapping("products/low/{low}/high/{high}")
    public List<Product> getProductsInPriceRange(@PathVariable double low,@PathVariable double high ){
        return productService.getProductsInPriceRange(low,high);
    }

    // get count oof all the prodcuts
    @GetMapping("products/count")
    public Integer getProductsCount(){
        return productService.getProductsCount();
    }

    // get products based on category
    @GetMapping("products/category/{category}")
    public List<Product> getProductsByCategory(@PathVariable Category category){
        return productService.getProductsByCategory(category);
    }

    // get count by catogery
    @GetMapping("products/count/{category}")
    public Integer getProductsCountByCategory(@PathVariable Category category){
        return productService.getProductsCountByCategory(category);
    }

    // get products in a particular price range and category
    @GetMapping("products/low/{low}/high/{high}/{category}")
    public List<Product> getProductsInPriceRangeAndCategory(@PathVariable double low,@PathVariable double high,@PathVariable Category category ){
        return productService.getProductsInPriceRangeAndCategory(low,high,category);
    }

    // get prodict by name
    @GetMapping("products/name/{name}")
    public  List<Product> getProductById(@PathVariable @Size(min=4) String name){
        return productService.getProductByName(name);
    }


    //POST

    // add single product§
    @PostMapping("product")
    public String addProduct(@RequestBody Product product){
        productService.addProduct(product);
        return "added";
    }


    // add multiple products

    @PostMapping("products")
    public String addProducts(@RequestBody @Valid List<Product> products){
        productService.addProducts(products);
        return "added" + products.size() + "products";
    }

    //PUT

    // increase/decrease price by category
    @PutMapping("products/category/{category}/increaseOrDeacrease/{increasOrDeacrease}/percentage{discount}")
    public String updatePriceByCategory(@PathVariable IncreasOrDeacrease increasOrDeacrease,@PathVariable Category category,@PathVariable float discount){
        return productService.updatePriceByCategory(increasOrDeacrease,category,discount);
    }

    // incraese/deacrese price by id
    @PutMapping("products/id/{id}/increaseOrDeacrease/{increasOrDeacrease}/percentage/{discount}")
    public String updatePriceById(@PathVariable IncreasOrDeacrease increasOrDeacrease,@PathVariable Integer id,@PathVariable float discount){
        return productService.updatePriceById(id,increasOrDeacrease,discount);
    }



    //DElETE

    // remove single product
    @DeleteMapping("product/id/{id}")
    public String deleteProduct(@PathVariable Integer id){
        return productService.deleteProduct(id);
    }

    // remove muliple products
    @DeleteMapping("products/ids")
    public String deleteProduct(@RequestBody List<Integer>  idList){
        return productService.deleteProducts(idList);
    }

    // remove all products based on category

    @DeleteMapping("products/{category}")
    public String deleteAllProductByCategory(@PathVariable Category category){
        return productService.deleteAllProductsByCategory(category);
    }

}
