package com.api.module.blog.repositories;

import com.api.module.blog.models.CommentBlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CommentBlogRepository extends JpaRepository<CommentBlog, UUID> {
}
