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
import java.util.Objects;

@Table(name="p2_users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", dateJointed=" + dateJointed +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(email, user.email) && Objects.equals(dateJointed, user.dateJointed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, email, dateJointed);
    }
}
