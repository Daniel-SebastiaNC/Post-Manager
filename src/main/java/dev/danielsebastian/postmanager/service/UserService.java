package dev.danielsebastian.postmanager.service;

import dev.danielsebastian.postmanager.dto.user.LoginResponse;
import dev.danielsebastian.postmanager.dto.user.UserLoginRequest;
import dev.danielsebastian.postmanager.dto.user.UserRegisterRequest;
import dev.danielsebastian.postmanager.exception.UsernameOrPasswordInvalidException;
import dev.danielsebastian.postmanager.mapper.user.UserMapper;
import dev.danielsebastian.postmanager.model.User;
import dev.danielsebastian.postmanager.repository.UserRepository;
import dev.danielsebastian.postmanager.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

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

    public LoginResponse loginUser(UserLoginRequest loginRequest){
        try {
            UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password());
            Authentication authentication = authenticationManager.authenticate(userAndPass);

            User user = (User) authentication.getPrincipal();

            return new LoginResponse(tokenService.generateToken(user));

        } catch (BadCredentialsException e) {
            throw  new UsernameOrPasswordInvalidException("Invalid email or password");
        }
    }
}
