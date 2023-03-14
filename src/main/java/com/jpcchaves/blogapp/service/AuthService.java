package com.jpcchaves.blogapp.service;


import com.jpcchaves.blogapp.payload.LoginDto;
import com.jpcchaves.blogapp.payload.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);
}
