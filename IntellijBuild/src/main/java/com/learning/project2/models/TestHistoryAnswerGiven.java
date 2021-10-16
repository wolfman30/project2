package com.learning.project2.models;


import com.learning.project2.models.test.TestAnswer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name="p2_test_history_ans_given")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestHistoryAnswerGiven {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "test_history_id", nullable = false)
    private TestHistory testHistory;

    @ManyToOne(optional = false)
    @JoinColumn(name = "test_answer_id", nullable = false)
    private TestAnswer testAnswer;

}
