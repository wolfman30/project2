package com.learning.project2.populate;

import com.learning.project2.models.user.User;
import com.learning.project2.models.user.UserController;
import com.learning.project2.models.user.UserRepository;

import java.time.Instant;

public class RunPopulate {

    public static void main(String[] args){

        UserController userController = new UserController();
        User testUser = new User(0l, "helloworld", "password", "test@email.com", Instant.now());

        userController.saveOrUpdate(testUser);
    }

}
