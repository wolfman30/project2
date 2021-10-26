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

@CrossOrigin(origins = WebLinks.ANGULAR_ORIGIN)
@RestController
@RequestMapping(path = "/user")
public class UserController {

    UserService userService;

    @Autowired
    private void setUserService(UserService userService){
        this.userService = userService;
    }

    public UserController(UserRepository repo){
        super();
        setUserService(new UserService(repo));
    }

    // path: BASEURL/user/create-or-update/
    //
    // Send in post request with user information and the service will
    // create or update the user based on the id provided (if no id is
    // provided it will create)
    @PostMapping(
            value="/create-or-update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<User> createOrUpdate(@RequestBody User user){
        return userService.createOrUpdate(user);
    }


    // path: BASEURL/user/login-attempt/
    //
    // Send in post request with user object with the username
    // and password of the attempt. Will return status of found
    // if the login attempt is successful and a JSON user object.
    // Returns status of 404 not found if not successful

    @PostMapping(
            value = "/login-attempt",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<User> loginAttempt(@RequestBody User user) {
        return userService.loginAttempt(user);
    }

}
