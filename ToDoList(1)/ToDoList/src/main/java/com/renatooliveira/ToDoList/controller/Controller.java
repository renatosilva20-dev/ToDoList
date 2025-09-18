package com.renatooliveira.ToDoList.controller;

import com.renatooliveira.ToDoList.model.Task;
import com.renatooliveira.ToDoList.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class Controller {
    private final Service taskService;

    public Controller(Service taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> all(){
        return taskService.findAll();
    }
    @GetMapping("/{id}")
    public Task byId(@PathVariable Long id){
        return taskService.findById(id);
    }
    @PostMapping
    public Task create(@RequestBody Task task){
        return taskService.createTask(task);
    }
    @PostMapping("/{id}")
    public Task update(@RequestBody Task task, @PathVariable Long id){
        return taskService.update(id,task);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        taskService.delete(id);
    }
    @PatchMapping("/{id}/complete")
    public Task complete(@PathVariable Long id){
        return taskService.markAsCompleted(id);
    }
}
