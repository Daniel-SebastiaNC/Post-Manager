package dev.danielsebastian.postmanager.repository;

import dev.danielsebastian.postmanager.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
