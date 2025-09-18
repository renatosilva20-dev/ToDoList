package com.renatooliveira.ToDoList.service;


import com.renatooliveira.ToDoList.model.Task;
import com.renatooliveira.ToDoList.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

@org.springframework.stereotype.Service
public class Service {
    @Autowired
    public TaskRepository taskRepository;

    public Task createTask(Task taskName){
        taskName.setCompleted(false); // vai começar como falso o estado da tarefa
        return taskRepository.save(taskName); // retorna o salvamento do nome da task
    }
    public List<Task> findAll(){
        return taskRepository.findAll();
        // lista todas as tarefas
    }

    public Task findById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("TaskNotFound"));
        //runtimeexception para mais facilidade de lancar a execption
    }

    public Task update(Long id, Task newTask){
        Task existing = findById(id);
        existing.setName(newTask.getName());
        existing.setDescprition(newTask.getDescprition());
        existing.setDateDue(newTask.getDateDue());
        existing.setCompleted(newTask.isCompleted());
        return taskRepository.save(newTask);

        // pegamos a tarefa ja existente e substituimos as informacoes com o newTask e retornamos essa newTask
    }

    public void delete(Long id){
        taskRepository.delete(findById(id));
    //deleta a tarefa pegando como paramentro o metodo de procurar pelo id e deleta
    }

    public Task markAsCompleted(Long id){
        Task task = findById(id);
        if (task.getDateDue() != null && task.getDateDue().isBefore(LocalDate.now())){
            throw new RuntimeException("TaskExpiredTime");
        }
        task.setCompleted(true);
        return taskRepository.save(task);
    // se a data de expiracao da tarefa nao for nula e ultrapassar o tempo, retorna uma exception de tempo expirado;
        // caso nao tenha expirado, a tarefa é marcada como feita e salva o estado.
    }

}
