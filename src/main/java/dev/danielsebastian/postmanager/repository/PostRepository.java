package dev.danielsebastian.postmanager.repository;

import dev.danielsebastian.postmanager.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUserId(Long userId);
}
