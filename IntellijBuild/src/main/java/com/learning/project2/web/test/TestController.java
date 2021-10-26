package com.learning.project2.web.test;

import com.learning.project2.web.WebLinks;
import com.learning.project2.web.test.models.DTO.TestSubmission;
import com.learning.project2.web.test.models.Test;
import com.learning.project2.web.test.models.history.TestHistory;
import com.learning.project2.web.test.services.TestHistoryService;
import com.learning.project2.web.test.services.TestService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/test")
@CrossOrigin(origins = WebLinks.ANGULAR_ORIGIN)
@NoArgsConstructor
public class TestController {

    private TestService testService;
    private TestHistoryService testHistoryService;

    @Autowired
    private void setTestService(TestService testService){
        this.testService = testService;
    }

    @Autowired
    private void setTestHistoryService(TestHistoryService testHistoryService){
        this.testHistoryService = testHistoryService;
    }

    public TestController(TestService testService, TestHistoryService testHistoryService){
        this();
        setTestService(testService);
        setTestHistoryService(testHistoryService);
    }

    // path: BASEURL/test/get/{id}
    //
    // Get request will return JSON of test object information
    // without any of the questions attached.
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

    // path: BASEURL/test/take/{id}/{count}
    //
    // Get request will return test JSON object with a
    // randomized assortment of questions (ex: if {count}
    // is replaced with 5, it will give five random questions.)
    // The answers object does not have a value for whether the
    // answer is correct or not.
    @GetMapping(
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

    // path: BASEURL/test/get_history/{id}
    //
    // Get the test history object associated with
    // Some instance of a user's test submission
    @GetMapping(
            path="/get_history/{id}",
            produces =
                    MediaType.APPLICATION_JSON_VALUE,
            consumes =
                    MediaType.TEXT_PLAIN_VALUE
    )
    public ResponseEntity<TestHistory> getTestHistory(@PathVariable Long id){
        return testHistoryService.getTestHistoryWithQuestions(id);
    }

    // path: BASEURL/test/get_history/user/{id}
    //
    // Get the test history object associated with
    // Some user id
    @GetMapping(
            path="/get_history/user/{id}",
            produces =
                    MediaType.APPLICATION_JSON_VALUE,
            consumes =
                    MediaType.TEXT_PLAIN_VALUE
    )
    public ResponseEntity<List<TestHistory>> getTestHistoryByUserId(@PathVariable Long id){
        return testHistoryService.getByUserId(id);
    }


    // path: BASEURL/test/submit
    //
    // Send a post request to the above url to commit object
    // to the database. The object in the body of the request
    // will be formated with the testId, userId, and list of
    // answers they gave.
    @PostMapping(
            value="/submit",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<TestHistory> submitTest(@RequestBody TestSubmission testSubmission){
        return testHistoryService.submitTest(testSubmission.asTestHistory());
    }
}
