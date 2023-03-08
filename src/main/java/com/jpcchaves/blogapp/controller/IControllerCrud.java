package com.jpcchaves.blogapp.controller;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IControllerCrud<Req, Res> {
    ResponseEntity<List<Res>> getAll();
    ResponseEntity<Req> getById(Long id);
    ResponseEntity<Res> create(Req request);
    ResponseEntity<Res> update(Req request);
    ResponseEntity<?> delete(Long id);
}
