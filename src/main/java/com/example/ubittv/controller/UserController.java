package com.example.ubittv.controller;

import com.example.ubittv.entity.UserEntity;
import com.example.ubittv.exception.UserAlreadyExistException;
import com.example.ubittv.exception.UserNotFoundException;
import com.example.ubittv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity registration(@RequestBody UserEntity user) {
        try {
            userService.registration(user);
            return  ResponseEntity.ok("Success");
        } catch (UserAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @GetMapping
    public ResponseEntity getOneUser(@RequestParam Long id) {
        try{
            userService.getOneUser(id);
            return ResponseEntity.ok(userService.getOneUser(id));
        } catch (UserNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id){
        try {
            return ResponseEntity.ok(userService.deleteUser(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("User not found");
        }
    }
}
