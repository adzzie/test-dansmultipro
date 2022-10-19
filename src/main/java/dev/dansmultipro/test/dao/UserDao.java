package dev.dansmultipro.test.dao;

import dev.dansmultipro.test.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);
}
