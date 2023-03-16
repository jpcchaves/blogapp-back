package com.jpcchaves.blogapp.service;

import com.jpcchaves.blogapp.payload.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto create(CategoryDto categoryDto);

    CategoryDto getById(Long categoryId);

    List<CategoryDto> getAll();

    CategoryDto update(Long categoryId, CategoryDto categoryDto);

    void delete(Long categoryId);
}
