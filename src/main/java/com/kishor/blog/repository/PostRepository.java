/**
 * 
 */
package com.kishor.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kishor.blog.entity.Post;

/**
 * @author Kishu
 *
 */
public interface PostRepository extends JpaRepository<Post, Long> {

}
