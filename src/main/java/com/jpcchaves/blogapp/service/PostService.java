package com.jpcchaves.blogapp.service;

import com.jpcchaves.blogapp.payload.PostDto;

import java.util.List;

public interface PostService {
    PostDto create(PostDto postDto);
    List<PostDto> getAll();
    PostDto getById(Long id);
}
