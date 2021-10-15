package com.learning.project2.models.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    private UserRepository userRepository;

    @Autowired
    private void setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public void saveOrUpdate(User user){
        userRepository.save(user);
    }

    @PostMapping(
            value = "login-attempt/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<User> login(@RequestBody User user) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        String username = user.getUsername();
        String password = user.getPassword();

        System.out.println(username+" "+password);
        System.out.println(user.toString());

        User login = userRepository.findByUsernameIgnoreCaseAndPassword(username, password);

        HttpHeaders httpHeaders = new HttpHeaders();
        if(login!=null) {
            return new ResponseEntity<>(login, httpHeaders, HttpStatus.FOUND);
        }
        return new ResponseEntity<>(null, httpHeaders, HttpStatus.NOT_FOUND);

    }



}
