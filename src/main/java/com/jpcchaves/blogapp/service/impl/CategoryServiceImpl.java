package com.jpcchaves.blogapp.service.impl;

import com.jpcchaves.blogapp.entity.Category;
import com.jpcchaves.blogapp.exception.ResourceNotFoundException;
import com.jpcchaves.blogapp.payload.CategoryDto;
import com.jpcchaves.blogapp.repository.CategoryRepository;
import com.jpcchaves.blogapp.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public CategoryDto getById(Long categoryId) {
        var category = repository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        return mapper.map(category, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAll() {
        var categories = repository.findAll();
        return categories
                .stream()
                .map(category ->
                        mapper.map(category, CategoryDto.class)
                )
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto update(Long categoryId, CategoryDto categoryDto) {
        var category = repository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        var updatedCategory = updateCategory(category, categoryDto);

        return mapper.map(repository.save(updatedCategory), CategoryDto.class);
    }

    private Category updateCategory(Category category, CategoryDto categoryDto) {
        category.setId(category.getId());
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        return category;
    }

}
