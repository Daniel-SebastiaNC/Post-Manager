package dev.danielsebastian.postmanager.service;

import dev.danielsebastian.postmanager.dto.user.UserRegisterRequest;
import dev.danielsebastian.postmanager.mapper.user.UserMapper;
import dev.danielsebastian.postmanager.model.User;
import dev.danielsebastian.postmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    public User registerUser(UserRegisterRequest userRegisterRequest) {
        User domain = userMapper.toDomain(userRegisterRequest);
        domain.setPassword(passwordEncoder.encode(domain.getPassword()));

        if (userRegisterRequest.password().equals(userRegisterRequest.confirmPassword())) {
            return userRepository.save(domain);
        }

        return null;
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
}
