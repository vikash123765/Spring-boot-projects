package com.vikash.stockAPP.controller;

import com.vikash.stockAPP.model.Stock;
import com.vikash.stockAPP.model.Type;
import com.vikash.stockAPP.service.StockService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
public class StockController {

    @Autowired
    StockService stockService;

    //post
    @PostMapping("stock")
    public Stock addStock(@RequestBody Stock stock){
       return stockService.addStock(stock);
    }

    @PostMapping("stocks")
    public String addStocks(@RequestBody List<Stock> myStocks)
    {
        return stockService.addStocks(myStocks);
    }

    //get

    @GetMapping("stocks")
    public List<Stock> getAllStocks()
    {
        return stockService.getAllStocks();
    }

    @GetMapping("stock/{id}")
    public Stock getStockById(@PathVariable Long id){
        return  stockService.getStockById(id);
    }

    @GetMapping("stocks/idList")
    public List<Stock> getStocksByIds(@RequestBody List<Long> ids){
        return  stockService.getStocksById(ids);
    }

    @GetMapping("Stock/exist/{id}")
    public boolean IfStockExist(@PathVariable Long id){
        return stockService.isStockExist(id);
    }


    @GetMapping("stocks/ByType/{Type}")
    public List<Stock> stocksByType(@PathVariable Type Type){

        return stockService.stocksByType(Type);
    }

    @GetMapping("stocks/Type{Type}/belowPrice/{price}")
    public List<Stock> stocksByTypeAndLessThenEqualPrice(@PathVariable Type Type, @PathVariable double price){

        return stockService.stocksByTypeAndLessThenEqualPrice(Type,price);
    }

    @GetMapping("stocks/greaterThan/ownerCount{count}/belowPrice/{price}")
    public List<Stock> stocksByCountGreaterThanAndLessThenEqualPrice(@PathVariable Integer count, @PathVariable double price){

        return stockService.stocksByCountGreaterThanEqualAndLessThenEqualPrice(count,price);
    }
    @GetMapping("stocks/name/{stockName}/count")
    public Integer countStocksByName(@PathVariable String stockName){
        return stockService.countStocksByName(stockName); //
    }
    @GetMapping("stocks/greaterThanEqual/ownerCount/{count}/type{type}/sortAscendTimeStamp")
    public List<Stock> stocksByGreaterThanCountAndTypeSortAscend(@PathVariable int count, @PathVariable Type type){
        return stockService.stocksByGreaterThanEqualCountAndTypeSortAscend(count,type);
    }

    @GetMapping("stocks/greaterThan/date/{date}/sortDescPrice")
    public List<Stock> stocksByGreaterThanDateSortDescPrice(@PathVariable LocalDateTime date){
        return stockService.stocksByGreaterThanDateSortDescPrice(date);
    }

    @GetMapping("stocks/notByType/{type}/notStartDate{before}/{after}/notByCount{count}/sortDescByPrice")
    public List<Stock> stocksNotByTypeAndNotStartDateAndNotByCountSortDescByPrice(@PathVariable Type type, @PathVariable LocalDateTime before,@PathVariable LocalDateTime after,@PathVariable Integer count){
        return stockService.stocksNotByTypeAndNotStartDateAndNotByCountSortDescByPrice(type,before,after,count);

    }






    //Delete
    @DeleteMapping("stocks")
    public String removeAllStocks(){
        return stockService.removeAllStocks();
    }

    @DeleteMapping("Stock/{id}")
    public String removeStockById(@PathVariable Long id){
        return stockService.removeStockById(id);
    }

    @DeleteMapping("stocks/ids")
    public String removeStocksByIds(@RequestBody List<Long> ids){
        return stockService.removeStocksByIds(ids);
    }



}
