package dev.danielsebastian.postmanager.service;

import dev.danielsebastian.postmanager.dto.post.PostRequest;
import dev.danielsebastian.postmanager.exception.DataNotFoundException;
import dev.danielsebastian.postmanager.mapper.post.PostMapper;
import dev.danielsebastian.postmanager.model.Post;
import dev.danielsebastian.postmanager.model.User;
import dev.danielsebastian.postmanager.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserService userService;
    private final PostMapper postMapper;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post createPost(PostRequest postRequest) {
        User userFound = userService.getUserById(postRequest.userId()).orElseThrow(() -> new DataNotFoundException("User not found"));
        Post domain = postMapper.toDomain(postRequest, userFound);
        return postRepository.save(domain);
    }

    public List<Post> getPostsByUserId(Long userId) {
        return postRepository.findByUserId(userId);
    }

    public Post getPostById(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new DataNotFoundException("Post not found"));
    }
}
