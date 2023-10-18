package com.vikash.todoapp.service;

import com.vikash.todoapp.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Service // creating object of todoService
public class TodoService {


    @Autowired  // checks if there is @Bean assigns object of that to todiList does depencency injecting here with the kind of object you have
    List<Todo> todoList;  // todoList is just s reference of List<Todo>

    public List<Todo> getAllTodos() {
        return todoList;
    }

    public Todo getTodoById(Integer todoId) {

        for (int i = 0; i < todoList.size(); i++) {
            if (todoList.get(i).getId().equals(todoId)) { // if a task get(i) field id getId() is equal to pathvariable
                return todoList.get(i);   // return that perticular task
            }
        }
        return null;
    }


    public List<Todo> getUrgentTasks() {
        List<Todo> urgentUndoneTodos = new ArrayList<>();  // list of undone todos
        for (Todo todo : todoList) {


            if (!todo.isDone()){
                urgentUndoneTodos.add(todo);
            }
        }
        urgentUndoneTodos.sort((t1,t2)-> t1.getDueDate().compareTo(t2.getDueDate()));  // tell it to sort then tell it how it should be sorted(if i get 2 todos this is how i want them to compare) if the date caller is less then what i have passed inside comapre to we will get negativa response and it will sort based on that(acending)
        return urgentUndoneTodos;
    }

    public List<Todo> getUndoneTodos() {
        List<Todo> undoneList = new ArrayList<>();  // create a new list to be able to return undone task
        for (Todo todo : todoList) {  // iteriate once over all the todos in todoList
            if (todo.isDone() == false)  // check which tasks are marked as false and store in todo
                undoneList.add(todo);         // add the undone tasks to to undonelist


        }
        return undoneList;     // reurning the list with undone tasks when you have scanned the whole list only then return thats why we have return oustide for loop
    }

    public List<Todo> getDoneTodos() {
        List<Todo> doneList = new ArrayList<>();  // create a new list to be able to return done task
        for (Todo todo : todoList) {  // iteriate once over all the todos in todoList
            if (!todo.isDone() == false)  // check which tasks are marked as true store in todo
                doneList.add(todo);         // add the done tasks  to donelist


        }
        return doneList;     // reurning the list with done tasks when you have scanned the whole list only then return thats why we have return oustide for loop
    }

    public List<Todo> doneOneTime() {
        List<Todo> doneOntime = new ArrayList<>(); // creating new list tostore todos done in time
        for (Todo todo:todoList){ // traversing trough the list
            if (todo.isDone() && todo.getDoneTimeStamp().isBefore(todo.getDueDate())){  // if task is doneTimetamp is  before due date
                doneOntime.add(todo);  // add those task to done on tume
            }

        }
        return doneOntime;  // return the done on time list
    }

    public List<Todo> notDoneOneTime() {

        List<Todo> notDoneOnTime = new ArrayList<>(); // creating new list tostore todos not done in time
        for (Todo todo:todoList){ // traversing trough the list
            if (todo.isDone() && todo.getDoneTimeStamp().isAfter(todo.getDueDate())){  // id task is doneTimeStamp is after  due date
                notDoneOnTime.add(todo);  // add those task to done on tume
            }

        }
        return notDoneOnTime;  // return the done on time list
    }

    public String addToDo(Todo myTodo) {
        myTodo.setCreationTimeStamp(LocalDateTime.now()); // will set creation time when adding a task
        todoList.add(myTodo); // adding string to list
        return "todo added";
    }

    public String addTodos(List<Todo> myTodos) {
        for (Todo todo : myTodos) {  // iterate over every element of myTodos  store it in todoList

            todo.setCreationTimeStamp(LocalDateTime.now());
            todoList.add(todo);  // take every element and append it to the list
            // todoList.addAll(myTodos); // adding list to another list

        }
        return myTodos.size() + "todos added";
    }

    public String updateTodo(String oldTaskContent, String newTaskContent) {
        for (int i = 0; i < todoList.size(); i++) {
            if (todoList.get(i).getContent().equals(oldTaskContent)) {


                todoList.get(i).setContent(newTaskContent);  // for a particular todo that matched with old content string we fetehced that todo, now we have that. after
                // this we are calling a setter and passing newTask(new content)
                return "content is updated";
            }
        }
        return null;
    }

    public String markTodoDone(Integer id) {
        for (Todo todo: todoList){

            if (todo.getId().equals(id)){
                if (!todo.isDone()){
                    todo.setDone(true);
                    todo.setDoneTimeStamp(LocalDateTime.now());
                    return "todo done";
                }
            }
            else{
                return "todo already marked done";
            }
        }
        return "todo not found";
    }

    public String markTodoUnDone(Integer id) {
        for (Todo todo: todoList){

            if (todo.getId().equals(id)){
                if (todo.isDone()){
                    todo.setDone(true);
                    todo.setDoneTimeStamp(LocalDateTime.now());
                    return "todo marked un-done";
                }
            }
            else{
                return "todo already marked un-done";
            }
        }
        return "todo not found";
    }

    public String increaseDueDate(Integer id, Integer numDays) {
        for (Todo todo : todoList) {

            if (todo.getId().equals(id)) {   // if id matches
                LocalDateTime currentDueDate = todo.getDueDate(); // getting current due date

                // add number of days to orgiginal due date
                LocalDateTime newdate = todo.getDueDate().plusDays(numDays); // add num days to the original due date via plusDays method
                todo.setDueDate(newdate);  // setting that new due date
                return "due date is updated ";
            }
        }
        return "todo not found";
    }

    public String deleteTodo(Integer id) {
        for (int i = 0; i < todoList.size(); i++) {
            Todo todo = todoList.get(i); // creating variable todo
            if (todo.getId().equals(id)) {
                todoList.remove(i);
                return "todo removed";
            }
        }
        return "todo not dound";
    }

    public String removeTodos(List<Integer> idList) {
        int counter = 0;
        for (Integer id: idList){   // iterate over id list
            for (Todo todo : todoList){ // iterate over todozlist
                if (id.equals(todo.getId())){// if idlist id is equal to todoList id
                    todoList.remove(todo); // remove that todo
                    counter++;    // incremment each time todo is deleted
                    break;    // break inner looop start from again, because iteration is not based on index it will hit all the elemnt in the todoLsit
                }
            }
        }

        return counter + " todos where removed"; // when all the elements to be removed are found remove
    }

    public String cleanUndoneTasks(Integer limit) {
        int counter = 0;
        for (int i = 0; i < todoList.size() ; i++) {
            Todo todo = todoList.get(i);
            Duration diff = Duration.between(LocalDateTime.now(),todo.getDueDate()); // diffrence betwwen time noww and due date
            if (!todo.isDone() && Math.abs(diff.toDays())>= limit){  // if task is undone and the absolute value(actual diffrence betwwen time noww and due date) id more then the limit user passes
                // we cant use comare to because it will return a - + 0 value not the actual  diffrence thats whywe need absolute value insted. and we cant use diff.toDays only becuase w edont know if it will do LocalDateTime.now()- todo.getDueDate()) or the other way oround
                // based on that it will give positive or negative value thats why we nee to calculate absolute value insted which will always be postivive.
                todoList.remove(todo);  // delete that todo
                counter++;
            }
        }
        return counter + " undone expired todos were removed ";
    }

    public String cleanDoneTasks(Integer limit) {
        int counter = 0;
        for (int i = 0; i < todoList.size() ; i++) {
            Todo todo = todoList.get(i);

            if (todo.isDone()){  // only check the diffrence locla date time and donetimestamp of the tasks that are done

                Duration diff = Duration.between(LocalDateTime.now(),todo.getDoneTimeStamp());   //diffrence locla date time and donetimestamp(tells us when marked as done)
                if (Math.abs(diff.toDays())>= limit )  // delete the task which are marked done after the certain time limit passssed by uder, so if absolute value is more  then limit delete, will calculate in days

                    todoList.remove(todo);
                counter++;
            }

        }
        return counter + " done expired todos were removed ";
    }
}

