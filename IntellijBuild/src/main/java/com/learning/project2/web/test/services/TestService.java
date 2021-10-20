package com.learning.project2.web.test.services;

import com.learning.project2.web.test.models.Test;
import com.learning.project2.web.test.models.TestAnswer;
import com.learning.project2.web.test.models.TestQuestion;
import com.learning.project2.web.test.models.history.TestHistory;
import com.learning.project2.web.test.repositories.TestHistoryRepository;
import com.learning.project2.web.test.repositories.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TestService {

    private TestRepository testRepository;
    private TestHistoryRepository testHistoryRepository;

    @Autowired
    private void setTestRepository(TestRepository testRepository){
        this.testRepository = testRepository;
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
            System.out.println("test returned null");
            return null;
        }
        if(count > t.getTestQuestions().size()){
            System.out.println("Count is out of bounds (too large) Count"+count+" Size"+t.getTestQuestions().size());
            return null;
        }
        if(count <= 1){
            System.out.println("Count is out of bounds (too small) Count"+count);
            System.out.println("test returned null");
            return null;
        }

        //Randomize
        Collections.shuffle(t.getTestQuestions());
        //Select
        t.setTestQuestions(t.getTestQuestions().subList(0,count));

        return t;

    }
}
