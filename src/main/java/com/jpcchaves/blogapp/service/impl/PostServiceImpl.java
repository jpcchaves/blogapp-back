package com.jpcchaves.blogapp.service.impl;

import com.jpcchaves.blogapp.entity.Post;
import com.jpcchaves.blogapp.exception.ResourceNotFoundException;
import com.jpcchaves.blogapp.payload.PostDto;
import com.jpcchaves.blogapp.repository.PostRepository;
import com.jpcchaves.blogapp.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private ModelMapper mapper;

    public PostServiceImpl(PostRepository postRepository, ModelMapper mapper) {
        this.postRepository = postRepository;
        this.mapper = mapper;
    }

    @Override
    public PostDto create(PostDto postDto) {
        var post = mapper.map(postDto, Post.class);
        return mapper.map(postRepository.save(post), PostDto.class);
    }

    @Override
    public Page<PostDto> getAll(Pageable pageable) {
        Page<Post> list = postRepository.findAll(pageable);
        return list.map(post -> mapper.map(post, PostDto.class));
    }

    @Override
    public PostDto getById(Long id) {
        var post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return mapper.map(post, PostDto.class);
    }

    @Override
    public PostDto update(Long id, PostDto postDto) {
        var post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postDto.setId(post.getId());
        mapper.map(postDto, post);
        return mapper.map(postRepository.save(post), PostDto.class);
    }

    @Override
    public void delete(Long id) {
        var post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);
    }
}
