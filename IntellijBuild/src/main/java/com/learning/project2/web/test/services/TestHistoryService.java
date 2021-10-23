package com.learning.project2.web.test.services;

import com.learning.project2.web.test.models.history.TestHistory;
import com.learning.project2.web.test.models.history.TestHistoryAnswerGiven;
import com.learning.project2.web.test.repositories.TestHistoryAnswerGivenRepository;
import com.learning.project2.web.test.repositories.TestHistoryRepository;
import com.learning.project2.web.test.repositories.TestRepository;
import org.hibernate.JDBCException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TestHistoryService {

    private TestHistoryRepository testHistoryRepository;
    private TestHistoryAnswerGivenRepository answerGivenRepository;

    @Autowired public void setTestHistoryRepository(TestHistoryRepository testHistoryRepository){
        this.testHistoryRepository = testHistoryRepository;
    }

    @Autowired public void setAnswerGivenRepository(TestHistoryAnswerGivenRepository answerGivenRepository){
        this.answerGivenRepository = answerGivenRepository;
    }


    public ResponseEntity<List<TestHistory>> getByUserId(Long id){
        try {
            List<TestHistory> th = this.testHistoryRepository.findByUser_Id(id);

            if (th != null) {
                return new ResponseEntity<>(th, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<TestHistory> submitTest(TestHistory testHistory) {
        try{
            //Get all answer objects
            List<TestHistoryAnswerGiven> answers = new ArrayList<>();
            answers.addAll(testHistory.getAnswers());

            //Remove the answers from the testHistory object
            testHistory.setAnswers(new ArrayList<>());

            //Save the TestHistory object
            TestHistory newTestHistory = testHistoryRepository.save(testHistory);

            //Save the answers
            for(TestHistoryAnswerGiven answerGiven : answers){
                answerGiven.setTestHistoryId(newTestHistory.getId());
                answerGivenRepository.save(answerGiven);
            }

            return new ResponseEntity<>(testHistory, HttpStatus.OK);

        }catch(JDBCException | DataIntegrityViolationException e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<TestHistory> getTestHistoryWithQuestions(Long id) {
        try {
            Optional<TestHistory> testHistory = testHistoryRepository.findById(id);
            if(testHistory.isPresent()){
                return new ResponseEntity<>(testHistory.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
