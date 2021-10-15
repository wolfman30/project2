package com.learning.project2.models.user;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Table(name="p2_users")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class User {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name = "username", nullable = false, length = 64)
    private String username;

    @Column(name = "pass", nullable = false, length = 12)
    private String password;

    @Column(name = "email", nullable = false, length = 64)
    private String email;

    @Column(name = "date_jointed")
    private Instant dateJointed;
}
