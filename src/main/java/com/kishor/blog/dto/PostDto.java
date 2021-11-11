package com.kishor.blog.dto;

import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


public class PostDto {

	private Long id;


	@NotEmpty
	@Size(min = 2,message = "Post title should be more than 2 characters in length.")
	private String title;

	
	@NotEmpty
	@Size(min = 20,message = "Post description should be more than 20 character in length.")
	private String description;

	@NotEmpty
	private String content;
	
	private Set<CommentDto> comments;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Set<CommentDto> getComments() {
		return comments;
	}

	public void setComments(Set<CommentDto> comments) {
		this.comments = comments;
	}
	
	
}
