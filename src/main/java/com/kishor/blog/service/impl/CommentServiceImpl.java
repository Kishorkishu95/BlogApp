/**
 * 
 */
package com.kishor.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.kishor.blog.dto.CommentDto;
import com.kishor.blog.entity.Comment;
import com.kishor.blog.entity.Post;
import com.kishor.blog.exception.BlogApiException;
import com.kishor.blog.exception.ResourceNotFoundException;
import com.kishor.blog.repository.CommentRepository;
import com.kishor.blog.repository.PostRepository;
import com.kishor.blog.service.CommentService;

/**
 * @author Kishu
 *
 */
@Service
public class CommentServiceImpl implements CommentService {

	private CommentRepository commentRepository;

	private PostRepository postRepository;
	
	private ModelMapper modelMapper;

	public CommentServiceImpl(PostRepository postRepository, CommentRepository commentRepository,ModelMapper modelMapper) {
		this.postRepository = postRepository;
		this.commentRepository = commentRepository;
		this.modelMapper=modelMapper;
	}

	private CommentDto mapToDto(Comment comment) {
		CommentDto dtoComment = modelMapper.map(comment, CommentDto.class);
		/*
		 * CommentDto commentDto = new CommentDto(); commentDto.setId(comment.getId());
		 * commentDto.setName(comment.getName()); commentDto.setBody(comment.getBody());
		 * commentDto.setEmail(comment.getEmail());
		 */
		return dtoComment;
	}

	private Comment mapToEntity(CommentDto dto) {
		Comment comment = modelMapper.map(dto, Comment.class);
		/*
		 * Comment comment1 = new Comment(); comment1.setId(dto.getId());
		 * comment1.setName(dto.getName()); comment1.setBody(dto.getBody());
		 * comment1.setEmail(dto.getEmail());
		 */
		return comment;
	}

	@Override
	public CommentDto createComment(Long postId, CommentDto commentDto) {
		Comment comment = mapToEntity(commentDto);
		Post getOne = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
		comment.setPost(getOne);
		Comment newComment = commentRepository.save(comment);
		CommentDto dtoComment = mapToDto(newComment);

		return dtoComment;
	}

	@Override
	public List<CommentDto> getAllCommentsOfPost(Long postId) {
		// TODO Auto-generated method stub
		List<Comment> commentsForPost = commentRepository.findByPostId(postId);

		return commentsForPost.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
	}

	@Override
	public CommentDto getCommentById(Long postId, Long commentId) {
		Comment comment = getPostCommentById(postId, commentId);
		return mapToDto(comment);
	}

	@Override
	public CommentDto updateCommentOfPost(Long postId, Long commentId, CommentDto commentRequestDto) {
		Comment comment = getPostCommentById(postId, commentId);
		comment.setName(commentRequestDto.getName());
		comment.setEmail(commentRequestDto.getEmail());
		comment.setBody(commentRequestDto.getBody());
		Comment updatedComment = commentRepository.save(comment);
		return mapToDto(updatedComment);
	}

	@Override
	public void deleteCommentForThePost(Long postId, Long commentId) {
		// TODO Auto-generated method stub
		Comment comment = getPostCommentById(postId, commentId);
		commentRepository.delete(comment);
		
	}

	/**
	 * @param postId
	 * @param commentId
	 * @return
	 */
	private Comment getPostCommentById(Long postId, Long commentId) {
		Post getOne = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
		Comment comment = commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "id", commentId));
		if(!comment.getPost().getId().equals(getOne.getId())){
			throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to a particular post");
		}
		return comment;
	}

}
