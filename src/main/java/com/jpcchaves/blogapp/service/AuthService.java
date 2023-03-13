package com.jpcchaves.blogapp.service;


import com.jpcchaves.blogapp.payload.LoginDto;
import org.springframework.stereotype.Service;

public interface AuthService {
    String login(LoginDto loginDto);
}
