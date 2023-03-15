package com.jpcchaves.blogapp.repository;

import com.jpcchaves.blogapp.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
