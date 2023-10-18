package com.example.vikash.hotelmanagment.dao;

import com.example.vikash.hotelmanagment.model.Room;
import com.example.vikash.hotelmanagment.model.Type;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRoomRepo extends CrudRepository<Room,Integer> {

    List<Room> findByRoomAvailable(boolean b);


    List<Room> findByRoomAvailableAndRoomType(boolean e, Type type);

 

    List<Room> findByRoomPriceLessThanEqualAndRoomType( double price,Type type);

    List<Room> findByRoomNumber(Integer number);
}
