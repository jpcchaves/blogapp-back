package com.jpcchaves.blogapp.service;

import com.jpcchaves.blogapp.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto create(Long postId, CommentDto comment);

    List<CommentDto> getCommentsByPostId(Long postId);
}
