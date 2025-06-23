package dev.danielsebastian.postmanager.dto.post;

import dev.danielsebastian.postmanager.dto.user.UserDto;

public record PostResponse(Long id, String title, String content, UserDto user) {
}
