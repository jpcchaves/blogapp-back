package com.jpcchaves.blogapp.service.impl;

import com.jpcchaves.blogapp.entity.Comment;
import com.jpcchaves.blogapp.exception.ResourceNotFoundException;
import com.jpcchaves.blogapp.payload.CommentDto;
import com.jpcchaves.blogapp.repository.CommentRepository;
import com.jpcchaves.blogapp.repository.PostRepository;
import com.jpcchaves.blogapp.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper mapper;


    @Override
    public CommentDto create(Long postId, CommentDto commentDto) {
        var comment = mapper.map(commentDto, Comment.class);
        var post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", "postId"));

        comment.setPost(post);
        commentRepository.save(comment);

        return mapper.map(comment, CommentDto.class);
    }
}
