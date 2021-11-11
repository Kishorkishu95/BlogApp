package com.kishor.blog.service;

import com.kishor.blog.dto.PostDto;
import com.kishor.blog.dto.PostResponse;

public interface PostService {
	PostDto createPost(PostDto postDto);
	PostResponse getAllPosts(int pageNo, int pageSize,String sortBy,String sortDir);
	PostDto getPostById(Long id);
	PostDto updatePost(Long id, PostDto dto);
	void deletePostById(Long id);

}
