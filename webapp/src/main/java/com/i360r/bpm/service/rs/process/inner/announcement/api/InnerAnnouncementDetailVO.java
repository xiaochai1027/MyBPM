package com.i360r.bpm.service.rs.process.inner.announcement.api;

import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.i360r.bpm.business.model.ProcessVariable;

public class InnerAnnouncementDetailVO {

	@ProcessVariable
	private String title;
	@ProcessVariable
	private String content;
	@ProcessVariable
	private String publishDate;
	@ProcessVariable
	private List<String> locationIds;
	@ProcessVariable
	private List<Integer> positionIds;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public List<String> getLocationIds() {
		return locationIds;
	}

	public void setLocationIds(List<String> locationIds) {
		this.locationIds = locationIds;
	}

	public List<Integer> getPositionIds() {
		return positionIds;
	}

	public void setPositionIds(List<Integer> positionIds) {
		this.positionIds = positionIds;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
