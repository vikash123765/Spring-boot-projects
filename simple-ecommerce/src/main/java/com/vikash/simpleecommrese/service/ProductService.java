package com.vikash.simpleecommrese.service;

import com.vikash.simpleecommrese.dao.ProductRepo;
import com.vikash.simpleecommrese.model.Category;
import com.vikash.simpleecommrese.model.IncreasOrDeacrease;
import com.vikash.simpleecommrese.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {
    @Autowired
    ProductRepo productRepo;

    public Map<Integer, Product> getAllProducts() {
        return productRepo.getProducts();
    }

    public void addProduct(Product product) {
        getAllProducts().put(product.getId(),product);
    }


    public void addProducts(List<Product> products) {
   Map<Integer,Product> existingProducts = getAllProducts();
   for(Product product: products){
       existingProducts.put(product.getId(),product);
   }
    }

    public Product getProductById(Integer id) {
       for (Integer key : getAllProducts().keySet()){
           if(key.equals(id)){
               return getAllProducts().get(key);
           }
       }
       return null;
    }

    public String deleteProduct(Integer id) {
        for (Integer key : getAllProducts().keySet()) {

            if (getAllProducts().get(key).getId().equals(id)){
                getAllProducts().remove(key);
                return "todo removed";
}

        }
                return "todo not found ";




}

    public String deleteProducts(List<Integer> idList) {
        int counter = 0;
        for(Integer id: idList){
            for (Integer key : getAllProducts().keySet()){
                if(id.equals(key)){
                    getAllProducts().remove(key);
                    counter++;
                    break;
                }
            }
        }
        return counter + "rodects where removed";

    }

    public List<Product> getProductsInPriceRange(double low, double high) {
        if(low > high){
            throw new IllegalStateException("price range invalid");
        }
        Map<Integer,Product> allProducts = getAllProducts();  // local referance of the getAllProducts() method
        List<Product> productsInPriceRAnge = new ArrayList<>(); // creating list where we store the products in range

        for (Integer key: allProducts.keySet()){   // iterate over get all elements in hashmap  via keyset

            double productPrice =allProducts.get(key).getPrice();  // for this key get the product and its price

            if (productPrice >= low && productPrice <= high){     // if price is in range

                productsInPriceRAnge.add(allProducts.get(key));  // add those prodcuts to productsInPriceRAnge
            }
        }
        return productsInPriceRAnge; // reurn those åroduts that are in rnage
    }

    public Integer getProductsCount() {
        return getAllProducts().size();
    }


    public List<Product> getProductsByCategory(Category category) {
         List<Product> productsByCategory = new ArrayList<>();  // where we store prodcuts that meet the conditoiin
        for (Integer key: getAllProducts().keySet()){   // iteratiin to all the element in hahsmap via keyset
            if (getAllProducts().get(key).getCategory().equals(category)){   // condition
               productsByCategory.add(getAllProducts().get(key)); // add to our productsByCategory
            }
        }
        return productsByCategory;  // return those that match the condition
    }

    public Integer getProductsCountByCategory(Category category) {

        return getProductsByCategory(category).size();  // using the getProductsByCategory method and checking its size

    }

    public List<Product> getProductsInPriceRangeAndCategory(double low, double high, Category category) {
        List<Product> productsInRangeAndCategory = new ArrayList<>();
        List<Product> productsInRange = getProductsInPriceRange(low,high);

        for(Product product : productsInRange){
            if (product.getCategory().equals(category)){
                productsInRangeAndCategory.add(product);
            }
        }
        return productsInRangeAndCategory;

    }

    public List<Product> getProductByName(String name) {

        List<Product> productByNaming = new ArrayList<>();

            for (Product product : getAllProducts().values()){
                if(product.getName().equals(name)){
                    productByNaming.add(product);
                }
            }
            return productByNaming;
        }

    public String deleteAllProductsByCategory(Category category) {
        List<Integer> keysToRemove = new ArrayList<>();  // keys of the products that needs to be removed

        for (Integer key : getAllProducts().keySet()){   // iterating to the hashmap to
            if(getAllProducts().get(key).getCategory().equals(category)){ // checking if condition is true
              keysToRemove.add(key);  // adding those to keys to remove
            }
        }
        for (Integer key:keysToRemove){  // iterating trough keys to remove
            getAllProducts().remove(key); // removing products from hahsmap where catogry matches which the category we pass
        }
        return category+ "products where removed";
    }

    public String updatePriceByCategory(IncreasOrDeacrease increasOrDeacrease, Category category, float discount) {
        int polarity =( increasOrDeacrease == IncreasOrDeacrease.INCREASE) ? 1:-1;

        for (Product product : getProductsByCategory(category)){

            double originalPrice = product.getPrice();
            double priceAltering = originalPrice * (discount / 100) * polarity;;
            double priceAfterAltering  = originalPrice + priceAltering;
            product.setPrice(priceAfterAltering);

        }
         return "price updated";
    }

    public String updatePriceById(Integer id, IncreasOrDeacrease increasOrDeacrease,float discount ) {
        int polarity =( increasOrDeacrease == IncreasOrDeacrease.INCREASE) ? 1:-1;
        Product product= getProductById(id) ;
        double originalPrice = product.getPrice();
        double priceAltering = originalPrice * (discount / 100) * polarity;;
        double priceAfterAltering  = originalPrice + priceAltering;
        product.setPrice(priceAfterAltering);
        return"product with id: " + id + " price was updated";
    }

}

