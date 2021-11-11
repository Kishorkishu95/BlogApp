package com.kishor.blog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kishor.blog.dto.CommentDto;
import com.kishor.blog.service.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {

	private CommentService commentService;

	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}

	@PostMapping("/posts/{postId}/comments")
	public ResponseEntity<CommentDto> createCommentForPost(@PathVariable(value = "postId") Long postId,
			@Valid @RequestBody CommentDto commentDto) {

		return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);

	}

	@GetMapping("/posts/{postId}/comments")
	public List<CommentDto> getAllCommentsForThePost(@PathVariable(value = "postId") Long postId) {
		return commentService.getAllCommentsOfPost(postId);

	}

	@GetMapping("/posts/{postId}/comments/{commentId}")
	public ResponseEntity<CommentDto> getCommentForPostById(@PathVariable(value = "postId") Long postId,
			@PathVariable(value = "commentId") Long commentId) {
		CommentDto commentForPost = commentService.getCommentById(postId, commentId);
		return new ResponseEntity<>(commentForPost, HttpStatus.OK);
	}

	@PutMapping("/posts/{postId}/comments/{commentId}")
	public ResponseEntity<CommentDto> updateCommentOfThePost(@PathVariable(value = "postId") Long postId,
			@PathVariable(value = "commentId") Long commentId, @Valid @RequestBody CommentDto commentReqDto) {
		CommentDto updateComment = commentService.updateCommentOfPost(postId, commentId, commentReqDto);
		return new ResponseEntity<>(updateComment, HttpStatus.OK);

	}

	@DeleteMapping("/posts/{postId}/comments/{commentId}")
	public ResponseEntity<String> deleteCommentOfThePost(@PathVariable(value = "postId") Long postId,
			@PathVariable(value = "commentId") Long commentId) {
		commentService.deleteCommentForThePost(postId, commentId);
		return new ResponseEntity<>("Comment of the post has been deleted.", HttpStatus.OK);

	}
}
