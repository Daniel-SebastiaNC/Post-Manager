package dev.danielsebastian.postmanager.controller;

import dev.danielsebastian.postmanager.dto.post.PostRequest;
import dev.danielsebastian.postmanager.dto.post.PostResponse;
import dev.danielsebastian.postmanager.mapper.post.PostMapper;
import dev.danielsebastian.postmanager.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final PostMapper postMapper;

    @PostMapping("/create")
    public ResponseEntity<PostResponse> createPost(@RequestBody PostRequest postRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                postMapper.toResponse(
                    postService.createPost(postRequest)
                )
        );
    }

    @GetMapping("/all")
    public ResponseEntity<List<PostResponse>> getAllPosts() {
        return ResponseEntity.ok(
                postService.getAllPosts().stream().map(postMapper::toResponse).toList()
        );
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<List<PostResponse>> getAllPostsByUser(@PathVariable Long id) {
        return ResponseEntity.ok(
                postService.getPostsByUserId(id).stream().map(postMapper::toResponse).toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok(postMapper.toResponse(postService.getPostById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponse> updatePost(@PathVariable Long id, @RequestBody PostRequest postRequest) {
        return ResponseEntity.ok(postMapper.toResponse(postService.updatePost(id, postRequest)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
