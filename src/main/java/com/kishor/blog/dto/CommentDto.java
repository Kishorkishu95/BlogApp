/**
 * 
 */
package com.kishor.blog.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author Kishu
 *
 */
public class CommentDto {

	private Long id;
	
	@NotEmpty
	@Email
	private String email;
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	@Size(max = 100, message ="Should not exceed more than 100 characters in length.")
	private String body;

	public CommentDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommentDto(String email, String name, String body) {
		super();
		this.email = email;
		this.name = name;
		this.body = body;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the body
	 */
	public String getBody() {
		return body;
	}

	/**
	 * @param body the body to set
	 */
	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "CommentDto [id=" + id + ", email=" + email + ", name=" + name + ", body=" + body + "]";
	}

}
