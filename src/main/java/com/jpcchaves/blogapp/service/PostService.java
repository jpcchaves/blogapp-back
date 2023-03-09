package com.jpcchaves.blogapp.service;

import com.jpcchaves.blogapp.payload.PostDto;

import java.util.List;

public interface PostService {
    PostDto create(PostDto postDto);
    List<PostDto> getAll(int pageNo, int pageSize);
    PostDto getById(Long id);
    PostDto update(Long id, PostDto postDto);
    void delete(Long id);
}
