package com.jpcchaves.blogapp.controller;

import com.jpcchaves.blogapp.payload.CommentDto;
import com.jpcchaves.blogapp.service.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable(value = "postId") Long postId, @RequestBody CommentDto commentDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.create(postId, commentDto));
    }

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentDto>> findAll(@PathVariable(value = "postId") Long postId) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getCommentsByPostId(postId));
    }
}
