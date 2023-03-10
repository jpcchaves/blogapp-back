package com.jpcchaves.blogapp.controller;

import com.jpcchaves.blogapp.payload.PostDto;
import com.jpcchaves.blogapp.payload.PostResponse;
import com.jpcchaves.blogapp.service.PostService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/api/v1/posts")
    public ResponseEntity<PostDto> create(@Valid @RequestBody PostDto request) {
        return new ResponseEntity<>(postService.create(request), HttpStatus.CREATED);
    }

    @GetMapping("/api/v1/posts")
    public ResponseEntity<PostResponse> getAll(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getAll(pageable));
    }

    @GetMapping(value = "/api/v1/posts/{id}")
    public ResponseEntity<PostDto> getById(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(postService.getById(id), HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/api/v1/posts/{id}")
    public ResponseEntity<PostDto> update(@PathVariable(value = "id") Long id,
                                          @Valid @RequestBody PostDto request) {
        return new ResponseEntity<>(postService.update(id, request), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/api/v1/posts/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        postService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
