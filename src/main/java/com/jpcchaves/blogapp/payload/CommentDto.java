package com.jpcchaves.blogapp.payload;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class CommentDto {
    private Long id;

    @NotEmpty(message = "Comment is required")
    private String name;

    @NotEmpty(message = "Email is required")
    @Email(message = "Provide a valid email")
    private String email;

    @NotEmpty(message = "Comment body is required")
    @Size(min = 10, message = "comment body must be at least 10 characters")
    private String body;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
