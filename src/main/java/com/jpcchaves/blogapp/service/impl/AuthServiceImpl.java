package com.jpcchaves.blogapp.service.impl;

import com.jpcchaves.blogapp.entity.Role;
import com.jpcchaves.blogapp.entity.User;
import com.jpcchaves.blogapp.exception.BlogAPIException;
import com.jpcchaves.blogapp.payload.LoginDto;
import com.jpcchaves.blogapp.payload.RegisterDto;
import com.jpcchaves.blogapp.repository.RoleRepository;
import com.jpcchaves.blogapp.repository.UserRepository;
import com.jpcchaves.blogapp.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String login(LoginDto loginDto) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginDto.getUsernameOrEmail(), loginDto.getPassword()
                ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "User logged in successfully";
    }

    @Override
    public String register(RegisterDto registerDto) {

        // verify if user already registered
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "User already registered");
        }

        // check if email already registered
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Email already registered");
        }

        User user = new User();
        user.setName(registerDto.getName());
        user.setEmail(registerDto.getEmail());
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Optional<Role> userRole = roleRepository.findByName("ROLE_USER");

        if (userRole.isPresent()) {
            roles.add(userRole.get());
        }

        userRepository.save(user);

        return "User registered successfully";
    }
}
