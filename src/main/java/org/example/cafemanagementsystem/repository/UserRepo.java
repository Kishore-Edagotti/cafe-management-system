package org.example.cafemanagementsystem.repository;

import jakarta.validation.constraints.Email;
import org.example.cafemanagementsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    User findByUsername(String username);

    User findByEmail(String email);


    boolean existsByUsername(String username);

    boolean existsByEmail(@Email(message = "Invalid email format") String email);
}
