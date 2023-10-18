package com.vikash.todoapp.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Todo {

    private Integer id;
    private String content;
    private boolean done;

    private LocalDateTime creationTimeStamp;

    private LocalDateTime dueDate;

    private LocalDateTime doneTimeStamp;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public LocalDateTime getCreationTimeStamp() {
        return creationTimeStamp;
    }

    public void setCreationTimeStamp(LocalDateTime creationTimeStamp) {
        this.creationTimeStamp = creationTimeStamp;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime getDoneTimeStamp() {
        return doneTimeStamp;
    }

    public void setDoneTimeStamp(LocalDateTime doneTimeStamp) {
        this.doneTimeStamp = doneTimeStamp;
    }
}
