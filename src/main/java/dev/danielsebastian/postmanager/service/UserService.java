package dev.danielsebastian.postmanager.service;

import dev.danielsebastian.postmanager.dto.user.UserDto;
import dev.danielsebastian.postmanager.dto.user.UserRegisterRequest;
import dev.danielsebastian.postmanager.mapper.UserMapper;
import dev.danielsebastian.postmanager.model.User;
import dev.danielsebastian.postmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserDto registerUser(UserRegisterRequest userRegisterRequest) {
        User domain = userMapper.toDomain(userRegisterRequest);

        System.out.println(domain);

        if (userRegisterRequest.password().equals(userRegisterRequest.confirmPassword())) {
            User save = userRepository.save(
                    domain
            );

            return userMapper.toDto(save);
        }

        return null;
    }
}
