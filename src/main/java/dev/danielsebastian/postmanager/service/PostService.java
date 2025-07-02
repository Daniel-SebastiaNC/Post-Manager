package dev.danielsebastian.postmanager.service;

import dev.danielsebastian.postmanager.dto.post.PostRequest;
import dev.danielsebastian.postmanager.dto.user.JWTUserData;
import dev.danielsebastian.postmanager.exception.DataNotFoundException;
import dev.danielsebastian.postmanager.mapper.post.PostMapper;
import dev.danielsebastian.postmanager.model.Post;
import dev.danielsebastian.postmanager.model.User;
import dev.danielsebastian.postmanager.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
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
        User userFound = this.getUserByAuth();
        Post domain = postMapper.toDomain(postRequest, userFound);
        return postRepository.save(domain);
    }

    public List<Post> getPostsByUserId(Long userId) {
        return postRepository.findByUserId(userId);
    }

    public Post getPostById(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new DataNotFoundException("Post not found"));
    }

    public Post updatePost(Long postId, PostRequest postRequest) {
        Post postFound = postRepository.findById(postId).orElseThrow(() -> new DataNotFoundException("Post not found"));

        postFound.setTitle(postRequest.title());
        postFound.setContent(postRequest.content());

        return postRepository.save(postFound);
    }

    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new DataNotFoundException("Post not found"));
        postRepository.delete(post);
    }

    private User getUserByAuth() {
        JWTUserData jwtUserData = (JWTUserData) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.getUserById(jwtUserData.id()).orElseThrow(() -> new DataNotFoundException("User not found"));
    }
}
