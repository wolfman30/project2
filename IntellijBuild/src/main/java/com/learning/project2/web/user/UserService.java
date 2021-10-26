package com.learning.project2.web.user;

import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    private void setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    UserService(UserRepository repo){
        super();
        this.setUserRepository(repo);
    }


    public ResponseEntity<User> createOrUpdate(User user) {
        try{
            System.out.println(user.toString());
            userRepository.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }catch(JDBCException | DataIntegrityViolationException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<User> loginAttempt(User user) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json");

            System.out.println(user.getUsername());
            String username = user.getUsername();
            String password = user.getPassword();

            User login = userRepository.findByUsernameIgnoreCaseAndPassword(username, password);

            if (login != null) {
                return new ResponseEntity<>(login, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
