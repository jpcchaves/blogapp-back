package com.jpcchaves.blogapp.controller.impl;

import com.jpcchaves.blogapp.controller.IControllerCrud;
import com.jpcchaves.blogapp.payload.PostDto;
import com.jpcchaves.blogapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/posts")
public class PostControllerImpl implements IControllerCrud<PostDto , PostDto> {

    @Autowired
    private PostService postService;

    @Override
    @GetMapping
    public ResponseEntity<List<PostDto>> getAll() {
        return new ResponseEntity<>(postService.getAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PostDto> getById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<PostDto> create(PostDto request) {
        return null;
    }

    @Override
    public ResponseEntity<PostDto> update(PostDto request) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        return null;
    }

//    @PostMapping
//    public ResponseEntity<PostDto> create(@RequestBody PostDto postDto) {
//        return new ResponseEntity<>(postService.create(postDto), HttpStatus.CREATED);
//    }
//
//    @GetMapping
//    public ResponseEntity<List<PostDto>> getAll() {
//        return new ResponseEntity<>(postService.getAll(), HttpStatus.OK);
//    }

}
