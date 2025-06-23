package dev.danielsebastian.postmanager.repository;

import dev.danielsebastian.postmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
