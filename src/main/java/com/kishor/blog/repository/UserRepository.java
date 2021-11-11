/**
 * 
 */
package com.kishor.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kishor.blog.entity.User;

/**
 * @author Kishu
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findUserByEmail(String email);
	Optional<User> findUserByUsernameOrEmail(String username,String email);
	Optional<User> findUserByUsername(String username);
	boolean existsUserByUsername(String username);
	boolean existsUserByEmail(String email);
}
