package dev.danielsebastian.postmanager.dto.user;

public record UserRegisterRequest(String username, String password, String confirmPassword) {
}
