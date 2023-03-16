package com.jpcchaves.blogapp.service.impl;

import com.jpcchaves.blogapp.entity.Category;
import com.jpcchaves.blogapp.entity.Post;
import com.jpcchaves.blogapp.exception.BlogAPIException;
import com.jpcchaves.blogapp.exception.ResourceNotFoundException;
import com.jpcchaves.blogapp.payload.PostDto;
import com.jpcchaves.blogapp.payload.PostResponse;
import com.jpcchaves.blogapp.repository.CategoryRepository;
import com.jpcchaves.blogapp.repository.PostRepository;
import com.jpcchaves.blogapp.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    private CategoryRepository categoryRepository;
    private ModelMapper mapper;

    public PostServiceImpl(PostRepository postRepository,
                           ModelMapper mapper,
                           CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.mapper = mapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public PostDto create(PostDto postDto) {

        checkIfPostByTitleAlreadyExists(postDto.getTitle());

        var category = checkIfCategoryExists(postDto.getCategoryId());

        var post = mapper.map(postDto, Post.class);

        post.setCategory(category);

        return mapper.map(postRepository.save(post), PostDto.class);
    }

    @Override
    public PostResponse getAll(Pageable pageable) {
        Page<Post> posts = postRepository.findAll(pageable);

        List<Post> listOfPosts = posts.getContent();

        List<PostDto> content = listOfPosts
                .stream()
                .map(post -> mapper.map(post, PostDto.class))
                .collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());

        return postResponse;
    }

    @Override
    public PostDto getById(Long id) {
        var post = checkIfPostExists(id);
        return mapper.map(post, PostDto.class);
    }

    @Override
    public PostDto update(Long id, PostDto postDto) {
        checkIfPostByTitleAlreadyExists(postDto.getTitle());

        var category = checkIfCategoryExists(postDto.getCategoryId());

        var post = checkIfPostExists(id);

        var updatedPost = updatePostAttributes(post, postDto, category);

        postRepository.save(updatedPost);

        return mapper.map(post, PostDto.class);
    }

    @Override
    public void delete(Long id) {
        var post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);
    }

    @Override
    public List<PostDto> getPostsByCategory(Long categoryId) {
        checkIfCategoryExists(categoryId);
        var posts = postRepository.findByCategoryId(categoryId);
        return posts.stream().map(post -> mapper.map(post, PostDto.class)).collect(Collectors.toList());
    }

    private Post updatePostAttributes(Post post, PostDto postDto, Category category) {
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        post.setCategory(category);
        return post;
    }

    private Category checkIfCategoryExists(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
    }

    private Post checkIfPostExists(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
    }

    private void checkIfPostByTitleAlreadyExists(String postTitle) {
        Optional<Post> postByTitle = Optional.ofNullable(postRepository.findPostByTitle(postTitle));

        if (postByTitle.isPresent()) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Post with title '" + postByTitle.get().getTitle() + "' already exists");
        }
    }
}
