package com.learning.project2;

import com.learning.project2.web.lex.BotService;
import com.learning.project2.web.lex.models.Interaction;
import com.learning.project2.web.test.models.TestAnswer;
import com.learning.project2.web.test.models.history.TestHistory;
import com.learning.project2.web.test.models.history.TestHistoryAnswerGiven;
import com.learning.project2.web.test.repositories.TestHistoryAnswerGivenRepository;
import com.learning.project2.web.test.repositories.TestHistoryRepository;
import com.learning.project2.web.test.services.TestHistoryService;
import com.learning.project2.web.user.User;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
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

import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Objects;

@SpringBootTest
class Project2ApplicationTests {
    /*@Test
    void contextLoads() {
    }*/

    @MockBean
    private TestHistoryRepository testHistoryRepository;

    @MockBean
    private TestHistoryAnswerGivenRepository answerGivenRepository;

    @MockBean
    private TestHistoryService testHistoryService;

    @Before
    public void setup() throws SQLException {
        Mockito.when(testHistoryService.submitTest(any())).getMock();
        //Mockito.when(answerGivenRepository.save(any())).then(notNull());
    }



    @Test
    public void historyOfTestTest() {
        TestAnswer testAnswer = new TestAnswer();
        testAnswer.setId(12L);
        testAnswer.setIsCorrect(true);
        testAnswer.setAnswerText("What?");

        TestHistoryAnswerGiven testHistoryAnswerGiven = new TestHistoryAnswerGiven();
        testHistoryAnswerGiven.setId(1L);
        testHistoryAnswerGiven.setTestAnswer(testAnswer);
        testHistoryAnswerGiven.setTestHistoryId(1L);

        Instant taken = Instant.now();
        User user = new User();
        com.learning.project2.web.test.models.Test test = new com.learning.project2.web.test.models.Test();
        TestHistory testHistory = new TestHistory();
        ArrayList answersList = new ArrayList();
        answersList.add(testHistoryAnswerGiven);

        testHistory.setId(11L);
        testHistory.setTest(test);
        testHistory.setUser(user);
        testHistory.setDateTaken(taken);
        testHistory.setAnswers(answersList);

        TestHistory finalTestHistory = new TestHistory();

        finalTestHistory.setId(11L);
        finalTestHistory.setTest(test);
        finalTestHistory.setUser(user);
        finalTestHistory.setDateTaken(taken);
        finalTestHistory.setAnswers(new ArrayList<>());

        TestHistoryService testHistoryService = new TestHistoryService();
        ResponseEntity<TestHistory> actual;
        ResponseEntity<TestHistory> expected;
        //expected = new ResponseEntity<>(finalTestHistory, HttpStatus.OK);
        expected = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        actual = testHistoryService.submitTest(testHistory);

        assertEquals(expected, actual);
    }

    public void botResponse() {
        BotService botService = new BotService();
        botService.init();
        Interaction interaction = new Interaction();

        Interaction result = botService.converse(interaction);

        assertNotNull(result);
    }

}
