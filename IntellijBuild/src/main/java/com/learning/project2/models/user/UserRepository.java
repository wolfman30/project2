package com.learning.project2.models.user;

import com.learning.project2.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameIgnoreCaseAndPassword(@NonNull String username, @NonNull String password);

    User findByEmailIgnoreCaseAndPassword(@NonNull String email, @NonNull String password);

    boolean existsByUsernameIgnoreCase(@NonNull String username);

    boolean existsByEmailIgnoreCase(@NonNull String email);

}
