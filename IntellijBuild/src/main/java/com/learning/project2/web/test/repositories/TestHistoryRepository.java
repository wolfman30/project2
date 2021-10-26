package com.learning.project2.web.test.repositories;

import com.learning.project2.web.test.models.TestQuestion;
import com.learning.project2.web.test.models.history.TestHistory;
import com.learning.project2.web.test.models.history.TestHistoryAnswerGiven;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestHistoryRepository extends JpaRepository<TestHistory, Long> {

    List<TestHistory> findByUser_Id(Long id);


    default double findAverageScoreByUser(long id){
        // Get the score of each test
        // and add them to the average
        int itteration = 0;
        double score = 0;

        List<TestHistory> tests = findByUser_Id(id);

        for(TestHistory th : tests){

            List<TestQuestion> questions = th.getTest().getTestQuestions();
            List<TestHistoryAnswerGiven> answers = th.getAnswers();

            for(TestHistoryAnswerGiven answer : answers){
                boolean isCorrect = answer.getTestAnswer().getIsCorrect();
                for(TestQuestion question : questions){
                    if(question.getTestAnswerList().contains(answer.getTestAnswer())){
                        if(isCorrect) {
                            score += question.getPoints();
                        }
                        break;
                    }
                }
                itteration++;
            }

        }

        return score/itteration;
    }
}
