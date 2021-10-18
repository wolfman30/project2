package com.learning.project2.web.test.models.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.learning.project2.web.test.models.Test;
import com.learning.project2.web.test.models.TestAnswer;
import com.learning.project2.web.test.models.history.TestHistory;
import com.learning.project2.web.test.models.history.TestHistoryAnswerGiven;
import com.learning.project2.web.user.User;
import lombok.Data;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
public class TestSubmission {
    private Long testId;
    private Long userId;

    @JsonProperty(value = "answers")
    private List<Long> answerId;

    public TestHistory asTestHistory(){
        Test test = new Test();
        test.setId(testId);

        User user = new User();
        user.setId(userId);


        TestHistory testHistory = new TestHistory();
        testHistory.setTest(test);
        testHistory.setUser(user);
        testHistory.setDateTaken(Instant.now());


        List<TestHistoryAnswerGiven> answers = new ArrayList<TestHistoryAnswerGiven>();
        testHistory.setAnswers(answers);

        for(Long id : answerId){
            TestAnswer answer = new TestAnswer();
            answer.setId(id);

            TestHistoryAnswerGiven answerGiven = new TestHistoryAnswerGiven();
            answerGiven.setTestAnswer(answer);

            answers.add(answerGiven);
        }

        return testHistory;
    }
}
