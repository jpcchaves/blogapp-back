package com.jpcchaves.blogapp.controller;

import com.jpcchaves.blogapp.payload.CommentDto;
import com.jpcchaves.blogapp.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable(value = "postId") Long postId,
                                             @Valid @RequestBody CommentDto commentDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.create(postId, commentDto));
    }

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentDto>> findAll(@PathVariable(value = "postId") Long postId) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getCommentsByPostId(postId));
    }

    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> findCommentById(@PathVariable(value = "postId") Long postId,
                                                      @PathVariable(value = "commentId") Long commentId) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getCommentById(postId, commentId));
    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable(value = "postId") Long postId,
                                                    @PathVariable(value = "commentId") Long commentId,
                                                    @Valid @RequestBody CommentDto commentDto) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.update(postId, commentId, commentDto));
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable(value = "postId") Long postId,
                                           @PathVariable(value = "commentId") Long commentId) {
        commentService.deleteComment(postId, commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
