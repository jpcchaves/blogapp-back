package com.jpcchaves.blogapp.service;

import com.jpcchaves.blogapp.payload.PostDto;

public interface PostService {
    PostDto createPost(PostDto postDto);
}
