package com.learning.project2.web.test.repositories;

import com.learning.project2.web.test.models.history.TestHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestHistoryRepository extends JpaRepository<TestHistory, Long> {

    List<TestHistory> findByUser_Id(Long id);



}
