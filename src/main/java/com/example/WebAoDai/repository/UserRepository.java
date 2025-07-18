package com.example.WebAoDai.repository;

import com.example.WebAoDai.entity.User;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@SpringBootApplication
public interface UserRepository extends JpaRepository<User, Long> {

    public User save(User user);

    Optional<User> findByEmail(String email);

    boolean existsByEmailAndPassword(String email, String password);
}
