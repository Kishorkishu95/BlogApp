package com.kishor.blog.service;

import java.util.List;

import com.kishor.blog.dto.CommentDto;

public interface CommentService {

	CommentDto createComment(Long postId, CommentDto commentDto);
	List<CommentDto> getAllCommentsOfPost(Long postId);
	CommentDto getCommentById(Long postId, Long commentId);
	CommentDto updateCommentOfPost(Long postId,Long commentId,CommentDto commentRequestDto);
	void deleteCommentForThePost(Long postId, Long commentId);
	
}
