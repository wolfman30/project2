package com.learning.project2.web.test.repositories;

import com.learning.project2.web.test.models.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {

    List<Test> findBySubject(String subject);

    @Override
    Optional<Test> findById(Long aLong);




}
