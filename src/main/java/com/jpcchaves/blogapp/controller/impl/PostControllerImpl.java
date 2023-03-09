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
public class PostControllerImpl implements IControllerCrud<PostDto, PostDto> {

    @Autowired
    private PostService postService;

    @Override
    @GetMapping
    public ResponseEntity<List<PostDto>> getAll(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
            ) {
        return new ResponseEntity<>(postService.getAll(pageNo,pageSize), HttpStatus.OK);
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<PostDto> getById(Long id) {
        return new ResponseEntity<>(postService.getById(id), HttpStatus.OK);
    }

    @Override
    @PostMapping
    public ResponseEntity<PostDto> create(@RequestBody PostDto request) {
        return new ResponseEntity<>(postService.create(request), HttpStatus.CREATED);
    }

    @Override
    @PutMapping(value = "/{id}")
    public ResponseEntity<PostDto> update(@PathVariable Long id, @RequestBody PostDto request) {
        return new ResponseEntity<>(postService.update(id, request), HttpStatus.OK);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        postService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
