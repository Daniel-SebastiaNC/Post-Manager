package dev.danielsebastian.postmanager.mapper.user;

import dev.danielsebastian.postmanager.dto.user.UserDto;
import dev.danielsebastian.postmanager.dto.user.UserRegisterRequest;
import dev.danielsebastian.postmanager.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toDomain(UserRegisterRequest userRegisterRequest) {
        User user = new User();
        user.setUsername(userRegisterRequest.username());
        user.setPassword(userRegisterRequest.password());
        return user;
    }

     public UserDto toDto(User user) {
        return new UserDto(user.getUsername());
    }
}
