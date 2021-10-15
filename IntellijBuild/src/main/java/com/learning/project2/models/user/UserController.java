package com.learning.project2.models.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "user")
public class UserController {

    private UserRepository userRepository;

    @Autowired
    private void setUserRepository(UserRepository ur){userRepository=ur;}

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public void saveOrUpdate(User user){
        userRepository.save(user);
    }

    public User login(String username, String password){
        return userRepository.findByUsernameIgnoreCaseAndPassword(username, password);
    }


}
