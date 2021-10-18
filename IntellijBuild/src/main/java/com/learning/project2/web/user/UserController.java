package com.learning.project2.web.user;

import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    private UserRepository userRepository;

    @Autowired
    private void setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @PostMapping(
            value="create-or-update/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<User> createOrUpdate(@RequestBody User user){
        try{
            userRepository.save(user);
            return new ResponseEntity<>(user, null, HttpStatus.OK);
        }catch(JDBCException | DataIntegrityViolationException e){
            return new ResponseEntity<>(null, null, HttpStatus.CONFLICT);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(
            value = "login-attempt/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<User> loginAttempt(@RequestBody User user) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json");

            String username = user.getUsername();
            String password = user.getPassword();

            User login = userRepository.findByUsernameIgnoreCaseAndPassword(username, password);

            HttpHeaders httpHeaders = new HttpHeaders();
            if (login != null) {
                return new ResponseEntity<>(login, httpHeaders, HttpStatus.FOUND);
            }
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return new ResponseEntity<>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
