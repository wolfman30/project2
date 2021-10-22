package com.learning.project2.web.user;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

@Table(name="p2_users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "username", length = 64)
    private String username;

    @Column(name = "pass", length = 12)
    private String password;

    @Column(name = "email", length = 64)
    private String email;

    @Column(name = "date_jointed")
    @CreationTimestamp
    private Instant dateJointed;

    @Column(name = "first_name", length = 32)
    private String firstName;

    @Column(name = "last_name", length = 40)
    private String lastName;


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
