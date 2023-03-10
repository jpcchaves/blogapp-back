package com.jpcchaves.blogapp.service;

import com.jpcchaves.blogapp.payload.CommentDto;

public interface CommentService {
    CommentDto create(Long postId, CommentDto comment);
}
