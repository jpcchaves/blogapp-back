package com.jpcchaves.blogapp.service;

import com.jpcchaves.blogapp.payload.PostDto;
import com.jpcchaves.blogapp.payload.PostResponse;
import org.springframework.data.domain.Pageable;

public interface PostService {
    PostDto create(PostDto postDto);

    PostResponse getAll(Pageable pageable);

    PostDto getById(Long id);

    PostDto update(Long id, PostDto postDto);

    void delete(Long id);
}
