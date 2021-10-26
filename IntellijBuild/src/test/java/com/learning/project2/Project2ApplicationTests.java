package com.learning.project2;

import com.learning.project2.web.lex.BotService;
import com.learning.project2.web.lex.models.Interaction;
import com.learning.project2.web.test.TestController;
import com.learning.project2.web.test.models.DTO.TestSubmission;
import com.learning.project2.web.test.models.TestAnswer;
import com.learning.project2.web.test.models.TestQuestion;
import com.learning.project2.web.test.models.history.TestHistory;
import com.learning.project2.web.test.models.history.TestHistoryAnswerGiven;
import com.learning.project2.web.test.repositories.TestHistoryAnswerGivenRepository;
import com.learning.project2.web.test.repositories.TestHistoryRepository;
import com.learning.project2.web.test.repositories.TestRepository;
import com.learning.project2.web.test.services.TestHistoryService;
import com.learning.project2.web.test.services.TestService;
import com.learning.project2.web.user.User;
import com.learning.project2.web.user.UserController;
import com.learning.project2.web.user.UserRepository;
import com.learning.project2.web.user.UserService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Null;
import org.mockito.stubbing.Answer;
import org.modelmapper.internal.util.Assert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.*;

import java.sql.Array;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SpringBootTest
class Project2ApplicationTests {
    /*@Test
    void contextLoads() {
    }*/



    @Test
    public void testUserObject(){
        User user = new User();
        user.setId(1L);
        user.setUsername("Hello");
        user.setPassword("Password");
        user.setEmail("Email");
        user.setDateJointed(Instant.parse("2018-11-30T18:35:24.00Z"));
        user.setFirstName("Fname");
        user.setLastName("Lname");

        Assertions.assertEquals(1L, user.getId());
        Assertions.assertEquals("Hello",user.getUsername());
        Assertions.assertEquals("Password",user.getPassword());
        Assertions.assertEquals("Email",user.getEmail());
        Assertions.assertEquals(Instant.parse("2018-11-30T18:35:24.00Z"),user.getDateJointed());
        Assertions.assertEquals("Fname",user.getFirstName());
        Assertions.assertEquals("Lname",user.getLastName());


        User otherUser = new User();
        otherUser.setId(2L);


        Assertions.assertNotNull(user.toString());
        Assertions.assertNotEquals(0, user.hashCode());
        Assertions.assertEquals(user, user);
        Assertions.assertNotEquals(user, otherUser);


    }

    @Test
    public void testGetUser(){

        UserRepository mockRepo = mock(UserRepository.class);
        UserController controller = new UserController(mockRepo);

        User user = new User(1L, "username", "password", "email", Instant.now(), "First", "Last");;

        when(mockRepo.save(user)).thenReturn(user);

        ResponseEntity<User> newUserResponse = controller.createOrUpdate(user);
        ResponseEntity<User> thisUserResponse = new ResponseEntity<>(user, HttpStatus.OK);

        // Test Update or Create User
        Assertions.assertEquals(thisUserResponse, newUserResponse);

        when(mockRepo.findByUsernameIgnoreCaseAndPassword(user.getUsername(), user.getPassword())).thenReturn(user);

        ResponseEntity<User> loginAttempt = controller.loginAttempt(user);

        //Test login attempt
        Assertions.assertEquals(thisUserResponse, loginAttempt);
    }


    @Test
    public void historyOfTestTest() {

        //Create objects
        TestAnswer testAnswer = new TestAnswer();
        testAnswer.setId(12L);
        testAnswer.setIsCorrect(true);
        testAnswer.setAnswerText("What?");

        TestHistoryAnswerGiven testHistoryAnswerGiven = new TestHistoryAnswerGiven();
        testHistoryAnswerGiven.setId(1L);
        testHistoryAnswerGiven.setTestAnswer(testAnswer);
        testHistoryAnswerGiven.setTestHistoryId(1L);

        com.learning.project2.web.test.models.Test test = new com.learning.project2.web.test.models.Test();
            test.setTestName("name");
            test.setSubject("subject");
            test.setId(1L);
            List<TestQuestion> testQuestions = new ArrayList<>();
                TestQuestion question = new TestQuestion();
                question.setId(0L);
                question.setQuestionText("Hello world");
                question.setDifficulty(2);
                question.setPoints(10.0);
                List<TestAnswer> testAnswerList = new ArrayList<>();
                    testAnswerList.add(testAnswer);
                question.setTestAnswerList(testAnswerList);
            testQuestions.add(question);
            test.setTestQuestions(testQuestions);

        Instant taken = Instant.now();
        User user = new User();
        TestHistory testHistory = new TestHistory();
            List<TestHistoryAnswerGiven> answersList = new ArrayList<>();
            answersList.add(testHistoryAnswerGiven);

            testHistory.setId(11L);
            testHistory.setTest(test);
            testHistory.setUser(user);
            testHistory.setDateTaken(taken);
            testHistory.setAnswers(answersList);

         List<TestHistory> usersTestHistoryList = new ArrayList<>();
        usersTestHistoryList.add(testHistory);

        TestSubmission testSubmission = new TestSubmission();
        testSubmission.setTestId(test.getId());
        testSubmission.setUserId(user.getId());
            List<Long> answerIds = new ArrayList<>();
            answerIds.add(testHistoryAnswerGiven.getId());
        testSubmission.setAnswerId(answerIds);

        // Mock the repos
        TestHistoryRepository mockTestHistoryRepo = mock(TestHistoryRepository.class);
        TestHistoryAnswerGivenRepository mockAnswersGivenRepo = mock(TestHistoryAnswerGivenRepository.class);
        TestRepository mockTestRepo = mock(TestRepository.class);

            // Mock test history
            when(mockTestHistoryRepo.findByUser_Id(user.getId())).thenReturn(usersTestHistoryList);
            when(mockTestHistoryRepo.save(any())).thenReturn(testHistory);
            when(mockTestHistoryRepo.findById(testHistory.getId())).thenReturn(java.util.Optional.of(testHistory));

            // Mock tests
            when(mockTestRepo.findById(test.getId())).thenReturn(java.util.Optional.of(test));

            // Mock answers
            when(mockAnswersGivenRepo.save(testHistoryAnswerGiven)).thenReturn(testHistoryAnswerGiven);

        // Inject the repos
        TestService testService = new TestService(mockTestRepo);
        TestHistoryService testHistoryService = new TestHistoryService(mockTestHistoryRepo, mockAnswersGivenRepo);

        TestController controller = new TestController(testService, testHistoryService);

        // Do Unit testing

        // Get test history by id
        Assertions.assertEquals(
                new ResponseEntity<>(testHistory, HttpStatus.OK),
                controller.getTestHistory(testHistory.getId()));

        // Get test history by id (404 not found)
        Assertions.assertEquals(
                new ResponseEntity<>(HttpStatus.NOT_FOUND),
                controller.getTestHistory(20L)
        );

        // Get test by id
        Assertions.assertEquals(
                new ResponseEntity<>(test, HttpStatus.OK),
                controller.getTestById(test.getId(),1)
        );

        // Get test without questions
        Assertions.assertEquals(
                new ResponseEntity<>(test, HttpStatus.OK),
                controller.getTestWithoutQuestions(test.getId())
        );

        // Get list of user's test histories
        Assertions.assertEquals(
                new ResponseEntity<>(usersTestHistoryList, HttpStatus.OK),
                controller.getTestHistoryByUserId(user.getId())
        );

        // Submit test
        Assertions.assertNotEquals(
                new ResponseEntity<>(testSubmission.asTestHistory(), HttpStatus.OK),
                controller.submitTest(testSubmission)
        );
    }


}
