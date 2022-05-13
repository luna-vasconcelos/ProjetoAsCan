package com.ascan.ascanflixapi.controller;

import com.ascan.ascanflixapi.model.User;
import com.ascan.ascanflixapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> listUsers(){

        return (List<User>) userRepository.findAll();
    }

    @GetMapping("/{id}") // o response entity me dá o estado da requisição
    public ResponseEntity<Optional<User>> searchForid(@PathVariable int id){
        Optional<User> user = userRepository.findById(id);
        return user.isPresent() ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<User> save(@Valid @RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(user));
    }
}
