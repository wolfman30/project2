package com.learning.project2.web.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameIgnoreCaseAndPassword(@NonNull String username, @NonNull String password);

    User findByEmailIgnoreCaseAndPassword(@NonNull String email, @NonNull String password);

    boolean existsByUsernameIgnoreCase(@NonNull String username);

    boolean existsByEmailIgnoreCase(@NonNull String email);

}
