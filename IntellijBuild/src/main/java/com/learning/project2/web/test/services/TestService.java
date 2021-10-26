package com.learning.project2.web.test.services;

import com.learning.project2.web.test.models.Test;
import com.learning.project2.web.test.models.TestAnswer;
import com.learning.project2.web.test.models.TestQuestion;
import com.learning.project2.web.test.models.history.TestHistory;
import com.learning.project2.web.test.repositories.TestHistoryRepository;
import com.learning.project2.web.test.repositories.TestRepository;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@NoArgsConstructor
@Slf4j
public class TestService {

    private TestRepository testRepository;

    @Autowired
    private void setTestRepository(TestRepository testRepository){
        this.testRepository = testRepository;
    }

    public TestService(TestRepository repo){
        this();
        setTestRepository(repo);
    }

    public Test getTestWithAnswersLabeled(Long id){
        Optional<Test> resp = testRepository.findById(id);
        return resp.orElse(null);
    }


    public Test getTestWithNoQuestions(Long id){
        Test test = getTestWithAnswersLabeled(id);
        List<TestQuestion> empty = Collections.emptyList();
        test.setTestQuestions(empty);
        return test;
    }

    public Test getTestWithAnswersUnlabeled(Long id){
        Test test = getTestWithAnswersLabeled(id);

        for(TestQuestion q : test.getTestQuestions()){
            for(TestAnswer a : q.getTestAnswerList()){
                a.setIsCorrect(null);
            }
        }

        return test;
    }

    public Test getTestWithRandomQuestionsSelected(Long id, int count){
        Test t = getTestWithAnswersUnlabeled(id);
        if(t==null){
            log.warn("test not found. Returned null");
            return null;
        }
        if(count > t.getTestQuestions().size()){
            log.warn("Count is out of bounds (too large) Count"+count+" Size"+t.getTestQuestions().size());
            log.warn("test returned null");
            return null;
        }
        if(count < 1){
            log.warn("Count is out of bounds (too small) Count"+count);
            log.warn("test returned null");
            return null;
        }

        //Randomize
        Collections.shuffle(t.getTestQuestions());
        //Select
        t.setTestQuestions(t.getTestQuestions().subList(0,count));

        return t;

    }
}
