package com.learning.project2.web.test.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Table(name="p2_test_questions")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "question_text", nullable = false, length = 180)
    private String questionText;

    @Column(name = "difficulty")
    private Integer difficulty;

    @Column(name = "points", nullable = false)
    private Double points;

    @OneToMany(fetch =FetchType.EAGER)
    @JoinColumn(name = "question_id")
    private List<TestAnswer> testAnswerList;

    @Override
    public String toString() {
        return "TestQuestion{" +
                "id=" + id +
                ", questionText='" + questionText + '\'' +
                ", difficulty=" + difficulty +
                ", points=" + points +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestQuestion that = (TestQuestion) o;
        return Objects.equals(id, that.id) && Objects.equals(questionText, that.questionText) && Objects.equals(difficulty, that.difficulty) && Objects.equals(points, that.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, questionText, difficulty, points);
    }
}
