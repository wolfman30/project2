package com.learning.project2.web.test.services;

import com.learning.project2.web.test.models.history.TestHistory;
import com.learning.project2.web.test.repositories.TestHistoryRepository;
import com.learning.project2.web.test.repositories.TestRepository;
import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TestHistoryService {

    private TestHistoryRepository testHistoryRepository;

    @Autowired
    public void setTestHistoryRepository(TestHistoryRepository testHistoryRepository){
        this.testHistoryRepository = testHistoryRepository;
    }

    public ResponseEntity<TestHistory> submitTest(TestHistory testHistory) {
        try{
            testHistoryRepository.save(testHistory);
            return new ResponseEntity<>(testHistory, HttpStatus.OK);
        }catch(JDBCException | DataIntegrityViolationException e){
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
