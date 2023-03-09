package com.jpcchaves.blogapp.service.impl;

import com.jpcchaves.blogapp.entity.Post;
import com.jpcchaves.blogapp.exception.ResourceNotFoundException;
import com.jpcchaves.blogapp.payload.PostDto;
import com.jpcchaves.blogapp.repository.PostRepository;
import com.jpcchaves.blogapp.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public PostDto create(PostDto postDto) {
        var post = mapper.map(postDto, Post.class);
        return mapper.map(postRepository.save(post), PostDto.class);
    }

    @Override
    public List<PostDto> getAll() {
        var posts = postRepository.findAll();
        return posts.stream().map(post -> mapper.map(post, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public PostDto getById(Long id) {
        var post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id.toString()));
        return mapper.map(post, PostDto.class);
    }

    @Override
    public PostDto update(Long id, PostDto postDto) {
        var post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id.toString()));
        postDto.setId(post.getId());
        mapper.map(postDto, post);
        return mapper.map(postRepository.save(post), PostDto.class);
    }

    @Override
    public void delete(Long id) {
        var post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id.toString()));
        postRepository.delete(post);
    }
}
