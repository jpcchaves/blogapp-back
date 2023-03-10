package com.jpcchaves.blogapp.service.impl;

import com.jpcchaves.blogapp.entity.Comment;
import com.jpcchaves.blogapp.exception.BlogAPIException;
import com.jpcchaves.blogapp.exception.ResourceNotFoundException;
import com.jpcchaves.blogapp.payload.CommentDto;
import com.jpcchaves.blogapp.repository.CommentRepository;
import com.jpcchaves.blogapp.repository.PostRepository;
import com.jpcchaves.blogapp.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private ModelMapper mapper;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper mapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.mapper = mapper;
    }
    
    @Override
    public CommentDto create(Long postId, CommentDto commentDto) {
        var comment = mapper.map(commentDto, Comment.class);
        var post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        comment.setPost(post);
        commentRepository.save(comment);

        return mapper.map(comment, CommentDto.class);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(Long postId) {
        var comments = commentRepository.findByPostId(postId);

        return comments.stream()
                .map(comment -> mapper.map(comment, CommentDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {
        var post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        var comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", commentId));

        if(!comment.getPost().getId().equals(post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to this post");
        }

        return mapper.map(comment, CommentDto.class);
    }

    @Override
    public CommentDto update(Long postId, Long commentId, CommentDto commentDto) {
        var post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        var comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", commentId));

        if(!comment.getPost().getId().equals(post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to this post");
        }

        comment.setPost(comment.getPost());
        comment.setBody(comment.getBody());
        comment.setEmail(comment.getEmail());
        comment.setName(comment.getName());

        commentRepository.save(comment);

        return mapper.map(comment, CommentDto.class);
    }
}
