package com.example.reafult.dto;

import java.util.List;

public class PagesDTO {

	private Integer id;
	private String pageName;
	private String title;
	private String location;
	private String listContent;
	private String subContent;
	private List<String> listUrlImage;
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getListContent() {
		return listContent;
	}
	public void setListContent(String listContent) {
		this.listContent = listContent;
	}
	public String getSubContent() {
		return subContent;
	}
	public void setSubContent(String subContent) {
		this.subContent = subContent;
	}
	
	public List<String> getListUrlImage() {
		return listUrlImage;
	}
	public void setListUrlImage(List<String> listUrlImage) {
		this.listUrlImage = listUrlImage;
	}
	@Override
	public String toString() {
		return "PagesDTO [id=" + id + ", pageName=" + pageName + ", title=" + title + ", listContent=" + listContent
				+ ", subContent=" + subContent + ", listUrlImage=" + listUrlImage + "]";
	}
	
}
