package com.learning.project2.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name="p2_tests")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "subject", nullable = false, length = 24)
    private String subject;

    @Column(name = "test_name", nullable = false, length = 64)
    private String testName;

}
