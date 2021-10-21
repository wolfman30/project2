package com.learning.project2.web.user;

import com.learning.project2.web.WebLinks;
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


    // path: BASEURL/user/create-or-update/
    //
    // Send in post request with user information and the service will
    // create or update the user based on the id provided (if no id is
    // provided it will create)
    @CrossOrigin(origins = WebLinks.ANGULAR_ORIGIN)
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

    // path: BASEURL/user/login-attempt/
    //
    // Send in post request with user object with the username
    // and password of the attempt. Will return status of found
    // if the login attempt is successful and a JSON user object.
    // Returns status of 404 not found if not successful
    @CrossOrigin(origins = WebLinks.ANGULAR_ORIGIN)
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
