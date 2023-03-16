package com.jpcchaves.blogapp.repository;

import com.jpcchaves.blogapp.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Post findPostByTitle(String title);

    List<Post> findPostByCategory(Long categoryId);
}
