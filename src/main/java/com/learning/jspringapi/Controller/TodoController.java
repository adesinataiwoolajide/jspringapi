package com.learning.jspringapi.Controller;

import com.learning.jspringapi.Model.Todo;
import com.learning.jspringapi.Repository.TodoRepository;
import com.learning.jspringapi.ResourceException.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TodoController {

    @Autowired
    TodoRepository todoRepository;

    @Autowired

    //Worked
    @GetMapping("/todos")
    public List<Todo> index(){
        return todoRepository.findAll();
    }

    //Worked
    @PostMapping("/todos/save")

    public Todo create(@Valid @RequestBody Todo save) {
        return todoRepository.save(save);
    }

    //worked Perfectly
    @GetMapping("/todos/{todoId}")
    public Todo getSingleTodo(@PathVariable(value = "todoId") Long todoId) {
        return todoRepository.findById(todoId)
                .orElseThrow(() -> new ResourceNotFoundException("Todo", "todoId", todoId));
    }

    @PutMapping("/todos/{todoId}")
    public Todo update(@PathVariable(value = "todoId") Long todoId,
                           @Valid @RequestBody Todo todo) {

        Todo todos = todoRepository.findById(todoId)
                .orElseThrow(() -> new ResourceNotFoundException("Todo", "todoId", todoId));

        todo.setName(todos.getName());
        todo.setContent(todos.getContent());
        Todo updatedTodo = todoRepository.save(todos);
        return updatedTodo;
    }


    //WOrked Perfectly
    @DeleteMapping("/todos/{todoId}")
    public ResponseEntity<?> destroy(@PathVariable(value = "todoId") Long todoId) {
        Todo todod = todoRepository.findById(todoId)
                .orElseThrow(() -> new ResourceNotFoundException("Todo", "todoId", todoId));
        todoRepository.delete(todod);
        return ResponseEntity.ok().build();
    }




}
