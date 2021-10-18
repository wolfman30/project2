package com.learning.project2.web.test.models.history;


import com.learning.project2.web.test.models.Test;
import com.learning.project2.web.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Table(name="p2_test_history")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(optional = false, cascade=CascadeType.MERGE)
    @JoinColumn(name = "test_id")
    private Test test;

    @ManyToOne(optional = false, cascade=CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "date_taken")
    private Instant dateTaken;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name = "test_history_id")
    private List<TestHistoryAnswerGiven> answers;


}
