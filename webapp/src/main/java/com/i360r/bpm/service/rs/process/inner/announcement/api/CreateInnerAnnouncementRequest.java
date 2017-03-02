package com.i360r.bpm.service.rs.process.inner.announcement.api;

import java.util.List;

import com.i360r.bpm.business.model.ProcessVariable;
import com.i360r.framework.service.aop.validate.field.annotation.NotNull;

public class CreateInnerAnnouncementRequest {

	@ProcessVariable(isKeyword=true, keywordOrder=1)
	@NotNull
	private String title;

	@ProcessVariable
	@NotNull
	private String content;

	@ProcessVariable
	@NotNull
	private String publishDate;

	@ProcessVariable
	@NotNull
	private List<String> locationIds;
	
	@ProcessVariable
	@NotNull
	private List<Integer> positionIds;

	public String getTitle() {
		return title;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
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

}
