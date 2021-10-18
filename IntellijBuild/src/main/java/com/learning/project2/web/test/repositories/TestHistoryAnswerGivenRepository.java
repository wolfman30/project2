package com.learning.project2.web.test.repositories;

import com.learning.project2.web.test.models.history.TestHistoryAnswerGiven;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestHistoryAnswerGivenRepository extends JpaRepository<TestHistoryAnswerGiven, Long> {

}
