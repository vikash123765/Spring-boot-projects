package com.vikash.todoapp.controller;

import com.vikash.todoapp.service.TodoService;
import com.vikash.todoapp.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController {

@Autowired
TodoService todoService; // creating object of TodoService, this is right now just a referance we need the @component in Todoservice
    //get
    @GetMapping("todos")
    public List<Todo> getAllTodos() {  // specify reurn type
        return todoService.getAllTodos();  // returning todolist to gett all todos
    }

    // get todo based on id
    @GetMapping("todos/{todoId}")
    public Todo getTodoById(@PathVariable Integer todoId) {
        return todoService.getTodoById(todoId);
    }

    // get urgent tasks, sort based on due date and should be marked as undone
    @GetMapping("todos/urgent")
    public List<Todo> getUrgentTasks(){    // returning a list of todos
        return todoService. getUrgentTasks();
    }


    // get all undone todos
    @GetMapping("todos/undone")
    public List<Todo> getUndoneTodos() {
        return todoService.getUndoneTodos();
    }

    // get all done todos
    @GetMapping("todos/done")
    public List<Todo> getDoneTodos() {
        return todoService.getDoneTodos();
    }

    // get all todos that where done on time
    @GetMapping("todos/onTime")
    public List<Todo> doneOneTime(){
        return todoService.doneOneTime();
        }

    // get all todos that where not done on time
    @GetMapping("todos/notOnTime")
    public List<Todo> notDoneOneTime(){
        return todoService.notDoneOneTime();
    }


    //post
    @PostMapping("todo")
    public String addToDo (@RequestBody Todo myTodo){  // return type String

            return todoService. addToDo(myTodo);
    }

    // add multiple todotos at once

    @PostMapping("todos")
    public String addTodos (@RequestBody List <Todo> myTodos) {  // passing in value for object

            return todoService.addTodos(myTodos);

    }

        // update

    @PutMapping("name/{oldTaskContent}/{newTaskContent}")
    public String updateTodo (@PathVariable String oldTaskContent, @PathVariable String newTaskContent){


            return todoService.updateTodo(oldTaskContent,newTaskContent);

    }
// mark todo as done
    @PutMapping("todo/done/{id}")
    String markTodoDone (@PathVariable Integer id){


        return todoService.markTodoDone(id);
    }

    // mark todo as undone
    @PutMapping("todo/undone/{id}")
    String markTodoUnDone (@PathVariable Integer id){

        return todoService.markTodoUnDone(id);
    }

    // increase num of due date by days
    @PutMapping("todo/increase/dueDate{id}/{numDays}")
    String increaseDueDate(@PathVariable Integer id, @PathVariable Integer numDays) {

        return todoService.increaseDueDate(id,numDays);
    }


//delete

        @DeleteMapping("todo/{id}")
        public String deleteTodo (@PathVariable Integer id){

        return todoService.deleteTodo(id);
        }



    // delete multiple todos
    @DeleteMapping("todos/ids")
    String removeTodos(@RequestBody List<Integer> idList) {// list of id thats why List<Integer>
        return todoService.removeTodos(idList);
    }

// delete   undone  todos that has passed the due date  based on user input time  limit

    @DeleteMapping("todos/undone/passedDueDate/{limit}")
    public String cleanUndoneTasks(@PathVariable Integer limit){
        return todoService.cleanUndoneTasks(limit);
    }

    // delete   done  todos that has passed the donetimestamp  based on user input time  limit
    @DeleteMapping("todo/done/timeLimit/{limit}")
    public String cleanDoneTasks(@PathVariable Integer limit){
        return todoService.cleanDoneTasks(limit);
        }

    }








