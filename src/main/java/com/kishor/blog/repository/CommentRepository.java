package com.kishor.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kishor.blog.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	List<Comment> findByPostId(Long postId);
}
