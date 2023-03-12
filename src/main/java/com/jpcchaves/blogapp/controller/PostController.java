package com.jpcchaves.blogapp.controller;

import com.jpcchaves.blogapp.payload.PostDto;
import com.jpcchaves.blogapp.payload.PostResponse;
import com.jpcchaves.blogapp.service.PostService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<PostResponse> getAll(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getAll(pageable));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PostDto> getById(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(postService.getById(id), HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping
    public ResponseEntity<PostDto> create(@Valid @RequestBody PostDto request) {
        return new ResponseEntity<>(postService.create(request), HttpStatus.CREATED);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping(value = "/{id}")
    public ResponseEntity<PostDto> update(@PathVariable(value = "id") Long id,
                                          @Valid @RequestBody PostDto request) {
        return new ResponseEntity<>(postService.update(id, request), HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        postService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
