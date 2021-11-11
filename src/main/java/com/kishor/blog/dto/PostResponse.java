package com.kishor.blog.dto;

import java.util.List;

public class PostResponse {
	private List<PostDto> content;
	private int pageNo;
	private int pageSize;
	private Long totalElements;
	private int totalPages;
	private boolean last;
	
	
	public PostResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public PostResponse(List<PostDto> content, int pageNo, int pageSize, Long totalElements, int totalPages,
			boolean last) {
		super();
		this.content = content;
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.totalElements = totalElements;
		this.totalPages = totalPages;
		this.last = last;
	}


	/**
	 * @return the content
	 */
	public List<PostDto> getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(List<PostDto> content) {
		this.content = content;
	}
	/**
	 * @return the pageNo
	 */
	public int getPageNo() {
		return pageNo;
	}
	/**
	 * @param pageNo the pageNo to set
	 */
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}
	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	/**
	 * @return the totalElements
	 */
	public Long getTotalElements() {
		return totalElements;
	}
	/**
	 * @param totalElements the totalElements to set
	 */
	public void setTotalElements(Long totalElements) {
		this.totalElements = totalElements;
	}
	/**
	 * @return the totalPages
	 */
	public int getTotalPages() {
		return totalPages;
	}
	/**
	 * @param totalPages the totalPages to set
	 */
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	/**
	 * @return the last
	 */
	public boolean isLast() {
		return last;
	}
	/**
	 * @param last the last to set
	 */
	public void setLast(boolean last) {
		this.last = last;
	}
	@Override
	public String toString() {
		return "PostResponse [content=" + content + ", pageNo=" + pageNo + ", pageSize=" + pageSize + ", totalElements="
				+ totalElements + ", totalPages=" + totalPages + ", last=" + last + "]";
	}
	
	
}
