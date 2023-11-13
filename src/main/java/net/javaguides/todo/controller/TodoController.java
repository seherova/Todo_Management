package net.javaguides.todo.controller;

import lombok.AllArgsConstructor;
import net.javaguides.todo.dto.TodoDto;
import net.javaguides.todo.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.DeleteExchange;

import java.util.List;





//HTTP isteklerini alıp işleyen ve HTTP yanıtlarını oluşturan bileşendir.
//İstekleri yönlendirir, veri işler ve sonuçları döndürür.
@RestController
@RequestMapping("api/todos")
@AllArgsConstructor
public class TodoController {
    private TodoService todoService;

    //Build Add Todo REST API

    @PostMapping //bu metodun sadece HTTP POST isteklerini karşılamak için kullanıldığını belirtir.
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto){ //@RequestBody annotasyonu, HTTP isteğinin gövdesindeki JSON verilerini TodoDto nesnesine dönüştürür.
        TodoDto savedTodo = todoService.addTodo(todoDto);
        return  new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }

    //Build GET Todo REST API
    @GetMapping("{id}")
    public  ResponseEntity<TodoDto> getTodo(@PathVariable("id") Long todoId){
        TodoDto todoDto = todoService.getTodo(todoId);
        return new ResponseEntity<>(todoDto, HttpStatus.OK);
    }

    //Build Get All Todos REST API

    @GetMapping
    public ResponseEntity<Object> getAllTodos(){
        List<TodoDto> todos = todoService.getAllTodos();
        //return new ResponseEntity<>(todos, HttpStatus.OK);
        return ResponseEntity.ok(todos);
    }

    //Build Update Todo REST API
    @PutMapping("{id}")
    public  ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto,@PathVariable("id") Long todoId){
        TodoDto updatedTodo = todoService.updateTodo(todoDto, todoId);
        return ResponseEntity.ok(updatedTodo);

    }

    //Build Delete Todo REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long todoId){
        todoService.deleteTodo(todoId);
        return ResponseEntity.ok("Todo deleted succesfully!");

    }

    //Build Complete Todo REST API
    @PatchMapping("{id}/complete")
    public ResponseEntity<TodoDto> completedTodo(@PathVariable("id") Long todoId){
        TodoDto updatedTodo = todoService.completeTodo(todoId);
        return ResponseEntity.ok(updatedTodo);
    }

    //Build in Complete Todo REST API
    @PatchMapping("{id}/in-complete")
    public ResponseEntity<TodoDto> inCompleteTodo(@PathVariable("id") Long todoId){
        TodoDto updatedTodo = todoService.incompleteTodo(todoId);
        return ResponseEntity.ok(updatedTodo);
    }



}
