package com.jpcchaves.blogapp.service.impl;

import com.jpcchaves.blogapp.entity.Category;
import com.jpcchaves.blogapp.payload.CategoryDto;
import com.jpcchaves.blogapp.repository.CategoryRepository;
import com.jpcchaves.blogapp.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository repository;
    private ModelMapper mapper;

    public CategoryServiceImpl(CategoryRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public CategoryDto create(CategoryDto categoryDto) {
        var category = mapper.map(categoryDto, Category.class);
        return mapper.map(repository.save(category), CategoryDto.class);
    }
}
