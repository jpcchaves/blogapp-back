package com.jpcchaves.blogapp.controller;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface IControllerCrud<Req, Res> {
    ResponseEntity<List<Res>> getAll(int pageNo, int pageSize);
    ResponseEntity<Req> getById(@PathVariable Long id);
    ResponseEntity<Res> create(@RequestBody Req request);
    ResponseEntity<Res> update(@PathVariable Long id, @RequestBody Req request);
    ResponseEntity<?> delete(@PathVariable Long id);
}
