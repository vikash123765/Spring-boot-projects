package com.vikash.stockAPP.dao;

import com.vikash.stockAPP.model.Stock;
import com.vikash.stockAPP.model.Type;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;


public interface IStockRepo extends CrudRepository<Stock,Long> {
    List<Stock> findByStockType(Type type);
    List<Stock> findByStockTypeAndStockPriceLessThanEqual(Type type, double price);

    List<Stock> findByStockName(String stockName);
    List<Stock> findByStockOwnerCountGreaterThanEqualAndStockPriceLessThanEqual(Integer count,double price);


    List<Stock> findByStockOwnerCountGreaterThanEqualAndStockTypeOrderByStockCreationTimeStamp(int count, Type type);

    List<Stock> findByStockCreationTimeStampGreaterThanOrderByStockPriceDesc(LocalDateTime date);

    List<Stock> findByStockTypeAndStockCreationTimeStampBeforeOrStockCreationTimeStampAfterStockOwnerCountNotInOrderByDesc(Type type, LocalDateTime before, LocalDateTime after, Integer count);

}
