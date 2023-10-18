package com.vikash.todoapp.repo;

import com.vikash.todoapp.model.Todo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class TodoFactory {

    @Bean   // creates an object and hold on to it and is sent to anter class via dependecy injection when recieving class has autowired
    List<Todo> initToDoList(){
        return new ArrayList<>();   // returning object giving array list of todos gives
          // an empty todo  we will use that somewhere else
    } // this is where i will be storing todos

}
