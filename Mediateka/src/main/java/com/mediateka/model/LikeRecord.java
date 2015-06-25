package com.mediateka.model;

import com.mediateka.annotation.Column;

public class LikeRecord {

	@Column(name = "id")
	private Integer id;

	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "content_group_id")
	private Integer contentGroupId;

	@Column(name = "state")
	private Integer state;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getContentGroupId() {
		return contentGroupId;
	}

	public void setContentGroupId(Integer contentGroupId) {
		this.contentGroupId = contentGroupId;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
	@Override
	public String toString() {
		return "LikeRecord [id=" + id + ", userId=" + userId
				+ ", contentGroupId=" + contentGroupId + ", state=" + state
				+ "]";
	}

}
