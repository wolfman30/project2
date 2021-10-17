package com.learning.project2.models.test;

import com.learning.project2.models.user.User;
import com.learning.project2.models.user.UserRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/test")
public class TestController {

    private TestRepository testRepository;

    @Autowired
    private void setTestRepository(TestRepository testRepository){
        this.testRepository = testRepository;
    }


    @GetMapping(
            path="/{id}",
            produces =
                    MediaType.APPLICATION_JSON_VALUE

    )
    public ResponseEntity<Test> getTestById(@PathVariable Long id){
        Optional<Test> resp = testRepository.findById(id);
        if(resp.isPresent()){
            Test test = resp.get();
            return new ResponseEntity<>(test, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}
