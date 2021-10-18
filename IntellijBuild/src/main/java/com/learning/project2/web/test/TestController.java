package com.learning.project2.web.test;

import com.learning.project2.web.test.models.Test;
import com.learning.project2.web.test.models.history.TestHistory;
import com.learning.project2.web.test.services.TestHistoryService;
import com.learning.project2.web.test.services.TestService;
import com.learning.project2.web.user.User;
import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/test")
public class TestController {

    private TestService testService;
    private TestHistoryService testHistoryService;

    @Autowired
    private void setTestService(TestService testService){
        this.testService = testService;
    }

    @GetMapping(
        path="get/{id}",
        produces =
                MediaType.APPLICATION_JSON_VALUE

    )
    public ResponseEntity<Test> getTestWithoutQuestions(@PathVariable Long id){
        Test test = testService.getTestWithNoQuestions(id);
        if(test==null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(test, HttpStatus.OK);
    }

    @PostMapping(
        path="/take/{id}/{count}",
        produces =
                MediaType.APPLICATION_JSON_VALUE,
        consumes =
                MediaType.TEXT_PLAIN_VALUE
    )
    public ResponseEntity<Test> getTestById(@PathVariable Long id, @PathVariable int count){

        Test resp = testService.getTestWithRandomQuestionsSelected(id,count);

        if(resp==null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PostMapping(
            value="/submit",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<TestHistory> submitTest(@RequestBody TestHistory testHistory){

        System.out.println("TestHistory:");
        System.out.println(testHistory);

        return testHistoryService.submitTest(testHistory);
    }
}
