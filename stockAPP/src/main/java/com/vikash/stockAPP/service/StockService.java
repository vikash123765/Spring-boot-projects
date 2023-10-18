package com.vikash.stockAPP.service;

import com.vikash.stockAPP.dao.IStockRepo;
import com.vikash.stockAPP.model.Stock;
import com.vikash.stockAPP.model.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class StockService {

    @Autowired
    IStockRepo stockRepo;

    // post

    public String addStocks(List<Stock> myStocks) {

        stockRepo.saveAll(myStocks);
        return "added";
    }

    public Stock addStock(Stock stock) {
       return stockRepo.save(stock);

    }

    // get
    public List<Stock> getAllStocks() {
        return (List<Stock>) stockRepo.findAll();
    }

    public List<Stock> getStocksById(List<Long> ids) {
        return (List<Stock>) stockRepo.findAllById(ids);
    }

    public boolean isStockExist(Long id) {
        return stockRepo.existsById(id);
    }

    public List<Stock> stocksByType(Type type) {

        return  stockRepo.findByStockType(type);
    }

    public List<Stock> stocksByTypeAndLessThenEqualPrice(Type type, double price) {
        return stockRepo.findByStockTypeAndStockPriceLessThanEqual(type,price);
    }

    public Integer countStocksByName(String stockName) {
       return stockRepo.findByStockName(stockName).size();
    }
    public Stock getStockById(Long id) {
        return stockRepo.findById(id).get(); // is how you use instead of optional ¨¨
    }


    public List<Stock> stocksByCountGreaterThanEqualAndLessThenEqualPrice(Integer count, double price) {
        return stockRepo.findByStockOwnerCountGreaterThanEqualAndStockPriceLessThanEqual(count,price);
    }


    public List<Stock> stocksByGreaterThanEqualCountAndTypeSortAscend(int count, Type type) {
        return stockRepo.findByStockOwnerCountGreaterThanEqualAndStockTypeOrderByStockCreationTimeStamp(count,type);
    }

    public List<Stock> stocksByGreaterThanDateSortDescPrice(LocalDateTime date) {
        return stockRepo.findByStockCreationTimeStampGreaterThanOrderByStockPriceDesc(date);
    }

    public List<Stock> stocksNotByTypeAndNotStartDateAndNotByCountSortDescByPrice(Type type, LocalDateTime before, LocalDateTime after, Integer count) {
        return stockRepo.findByStockTypeAndStockCreationTimeStampBeforeOrStockCreationTimeStampAfterStockOwnerCountNotInOrderByDesc(type,before,after,count);
    }
    // delete
    public String removeAllStocks() {
        stockRepo.deleteAll();
        return "all stocks where removed";
    }

    public String removeStockById(Long id) {
        stockRepo.deleteById(id);
        return "stock was removed";
    }

    public String removeStocksByIds(List<Long> ids) {
        stockRepo.deleteAllById(ids);
        return "stocks with given ids where removed";

    }




    }



















