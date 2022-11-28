package com.example.RestAPICrudAppSeriesEvents.controller;

import com.example.RestAPICrudAppSeriesEvents.model.User;
import com.example.RestAPICrudAppSeriesEvents.repository.UserRepository;
import com.example.RestAPICrudAppSeriesEvents.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.beans.Encoder;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8074")
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User _user = userService.saveUser(new User(user.getFirstname(), user.getLastname(), user.getEmail(),user.getPassword()));
        return new ResponseEntity<>(_user, HttpStatus.CREATED);
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        User user = userService.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        User _user = userService.findUserById(id);

        _user.setFirstname(user.getFirstname());
        _user.setLastname(user.getLastname());
        _user.setEmail(user.getEmail());
        _user.setPassword(user.getPassword());

        return new ResponseEntity<>(userService.saveUser(_user), HttpStatus.OK);
    }
    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
        userService.deleteUserById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
