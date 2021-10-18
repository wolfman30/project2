package com.learning.project2.web.test.models.history;


import com.learning.project2.web.test.models.Test;
import com.learning.project2.web.test.models.TestAnswer;
import com.learning.project2.web.test.models.history.TestHistory;
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

    @ManyToOne(optional = true)
    @JoinColumn(name = "test_answer_id", nullable = false)
    private TestAnswer testAnswer;

}
