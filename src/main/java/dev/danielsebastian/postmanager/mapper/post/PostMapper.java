package dev.danielsebastian.postmanager.mapper.post;

import dev.danielsebastian.postmanager.dto.post.PostRequest;
import dev.danielsebastian.postmanager.dto.post.PostResponse;
import dev.danielsebastian.postmanager.mapper.user.UserMapper;
import dev.danielsebastian.postmanager.model.Post;
import dev.danielsebastian.postmanager.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostMapper {
    private final UserMapper userMapper;

    public Post toDomain(PostRequest postRequest, User user) {
        Post post = new Post();
        post.setTitle(postRequest.title());
        post.setContent(postRequest.content());
        post.setUser(user);
        return post;
    }

    public PostResponse toResponse(Post post) {
        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                userMapper.toDto(post.getUser())
        );
    }
}
