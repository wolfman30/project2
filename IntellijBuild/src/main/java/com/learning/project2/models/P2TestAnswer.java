package com.learning.project2.models;

import javax.persistence.*;

@Table(name = "p2_test_answers")
@Entity
public class P2TestAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}