package com.kishor.blog.controller;


import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kishor.blog.dto.PostDto;
import com.kishor.blog.dto.PostResponse;
import com.kishor.blog.service.PostService;
import com.kishor.blog.utils.AppConstants;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	
	private PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}
	
//	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
		return new ResponseEntity<>(postService.createPost(postDto),HttpStatus.CREATED);
		
	}
	
	@GetMapping
	public PostResponse getAllPosts(@RequestParam(value = "pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
			@RequestParam(value = "pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIR, required = false) String sortDir

			){
		return postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PostDto> getSinglePostById(@PathVariable(name="id") Long id){
		
		return ResponseEntity.ok(postService.getPostById(id));
		
	}
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<PostDto> updateAPost(@PathVariable(name="id") Long id,@Valid @RequestBody PostDto postDto){
		return ResponseEntity.ok(postService.updatePost(id, postDto));
		
	}
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAPost(@PathVariable(name="id") Long id){
		postService.deletePostById(id);
		return new ResponseEntity<String>("Post has been deleted successfully.",HttpStatus.OK);
	}

}
