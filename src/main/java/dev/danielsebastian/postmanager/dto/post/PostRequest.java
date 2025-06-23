package dev.danielsebastian.postmanager.dto.post;

public record PostRequest(String title, String content, Long userId) {
}
