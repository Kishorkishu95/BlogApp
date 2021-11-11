package com.kishor.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.kishor.blog.dto.PostDto;
import com.kishor.blog.dto.PostResponse;
import com.kishor.blog.entity.Post;
import com.kishor.blog.exception.ResourceNotFoundException;
import com.kishor.blog.repository.PostRepository;
import com.kishor.blog.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	private PostRepository postRepository;
	private ModelMapper modelMapper;
	
	

	public PostServiceImpl(PostRepository postRepository,ModelMapper modelMapper) {
		this.postRepository = postRepository;
		this.modelMapper = modelMapper;
	}

	private PostDto mapToDto(Post post) {
		PostDto postDto = modelMapper.map(post, PostDto.class);
		/*
		 * PostDto newDtoPost = new PostDto(); newDtoPost.setId(post.getId());
		 * newDtoPost.setTitle(post.getTitle());
		 * newDtoPost.setContent(post.getContent());
		 * newDtoPost.setDescription(post.getDescription());
		 */
		return postDto;
	}

	private Post mapToEntity(PostDto dto) {
		
		Post post =modelMapper.map(dto, Post.class);
		/* 
		 * */
		return post;
	}

	@Override
	public PostDto createPost(PostDto postDto) {
		// Convert Dto to Entity
		Post post = mapToEntity(postDto);

		Post newPost = postRepository.save(post);
		// convert entity to Dto
		PostDto newDtoPostResponse = mapToDto(newPost);

		return newDtoPostResponse;
	}

	@Override
	public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
		// TODO Auto-generated method stub
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		Page<Post> posts = postRepository.findAll(pageable);
		List<Post> postsList = posts.getContent();
		List<PostDto> content = postsList.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
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
	public PostDto getPostById(Long id) {
		Post getOne = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id));
		return mapToDto(getOne);
	}

	@Override
	public PostDto updatePost(Long id, PostDto dto) {
		Post getOne = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id));
		getOne.setTitle(dto.getTitle());
		getOne.setContent(dto.getContent());
		getOne.setDescription(dto.getDescription());
		Post updatedPost = postRepository.save(getOne);
		return mapToDto(updatedPost);
	}

	@Override
	public void deletePostById(Long id) {
		Post getOne = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id));
		postRepository.delete(getOne);

	}

}
