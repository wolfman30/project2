package com.learning.project2.web.test.repositories;

import com.learning.project2.web.test.models.TestQuestion;
import com.learning.project2.web.test.models.history.TestHistory;
import com.learning.project2.web.test.models.history.TestHistoryAnswerGiven;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface TestHistoryRepository extends JpaRepository<TestHistory, Long> {

    List<TestHistory> findByUser_Id(Long id);


    default double findAverageScoreByUser(long id){
        // Get the score of each test
        // and add them to the average
        List<Double> scores = new ArrayList<>();

        List<TestHistory> tests = findByUser_Id(id);


        for(TestHistory th : tests){

            List<TestQuestion> questions = th.getTest().getTestQuestions();
            List<TestHistoryAnswerGiven> answers = th.getAnswers();

            double pointsEarned=0;
            double possiblePoints=0;

            for(TestHistoryAnswerGiven answer : answers){
                boolean isCorrect = answer.getTestAnswer().getIsCorrect();

                // Find the question that matches the answer
                for(TestQuestion question : questions){
                    System.out.println(question.toString());
                    if(question.getTestAnswerList().contains(answer.getTestAnswer())){
                        // If a question is found that has this as an answer
                        // Checks for if answer is correct
                        // then adds the points of the question
                        if(isCorrect) {
                            pointsEarned += question.getPoints();
                        }
                        possiblePoints += question.getPoints();
                        break;
                    }
                }
                scores.add(pointsEarned / possiblePoints);
            }
        }

        System.out.println(scores);
        double score = scores.stream().mapToDouble(s -> s).sum();

        return score/scores.size();
    }
}
