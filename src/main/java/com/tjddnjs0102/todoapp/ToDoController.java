package com.tjddnjs0102.todoapp;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class ToDoController {

    @Autowired
    private ToDoRepository toDoRepository;

    @PostMapping
    public ToDoEntity create(@RequestBody ToDoEntity toDoEntity) {
        return toDoRepository.save(toDoEntity);
    }

    @GetMapping
    public List<ToDoEntity> readAll() {
        return toDoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ToDoEntity readOne(@PathVariable Long id) {
        return toDoRepository.findById(id).orElseThrow(() -> new RuntimeException("ToDo not found"));
    }



    @PutMapping("/{id}")
    public ToDoEntity update(@PathVariable Long id,
                             @RequestBody ToDoEntity toDoEntity) {
        ToDoEntity existingToDo = toDoRepository.findById(id).orElseThrow(() -> new RuntimeException("ToDo not found"));
        BeanUtils.copyProperties(toDoEntity, existingToDo, "id");
        return toDoRepository.save(existingToDo);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        toDoRepository.deleteById(id);
    }

    @PatchMapping("/{id}")
    public ToDoEntity patch(@PathVariable Long id, @RequestBody ToDoEntity toDoEntity) {
        ToDoEntity existingToDo = toDoRepository.findById(id).orElseThrow(() -> new RuntimeException("ToDo not found"));
        if (toDoEntity.getTitle() != null) {
            existingToDo.setTitle(toDoEntity.getTitle());
        }
        if (toDoEntity.getTodoOrder() != null) {
            existingToDo.setTodoOrder(toDoEntity.getTodoOrder());
        }
        if (toDoEntity.getCompleted() != null) {
            existingToDo.setCompleted(toDoEntity.getCompleted());
        }
        return toDoRepository.save(existingToDo);
    }
}
