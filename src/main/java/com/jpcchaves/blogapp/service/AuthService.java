package com.jpcchaves.blogapp.service;


import com.jpcchaves.blogapp.payload.LoginDto;

public interface AuthService {
    String login(LoginDto loginDto);
}
