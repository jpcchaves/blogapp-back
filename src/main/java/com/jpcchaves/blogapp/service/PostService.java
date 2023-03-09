package com.jpcchaves.blogapp.service;

import com.jpcchaves.blogapp.payload.PostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {
    PostDto create(PostDto postDto);
    Page<PostDto> getAll(Pageable pageable);
    PostDto getById(Long id);
    PostDto update(Long id, PostDto postDto);
    void delete(Long id);
}
