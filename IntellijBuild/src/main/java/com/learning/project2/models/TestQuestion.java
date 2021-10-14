package com.learning.project2.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name="p2_test_questions")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestQuestion {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "test_id", nullable = false)
    private Test test;

    @Column(name = "question_text", nullable = false, length = 180)
    private String questionText;

    @Column(name = "difficulty")
    private Integer difficulty;

    @Column(name = "points", nullable = false)
    private Double points;

}
