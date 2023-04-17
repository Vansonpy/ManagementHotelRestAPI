package com.example.reafult.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Pages")
public class Pages {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "page_name")
	private String pageName;

	@Column(name = "title")
	private String title;

	@Column(name = "content",columnDefinition = "text")
	@Lob
	private String listContent;

	@Column(name = "sub_content")
	private String subContent;
	
	@Column(name = "location")
	private String location;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "page", cascade = CascadeType.ALL)
	private Set<FileDB> filesDB;

	public Pages() {
		super();
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Pages(Integer id, String pageName, String title, String listContent, String subContent, String location,
			Set<FileDB> filesDB) {
		super();
		this.id = id;
		this.pageName = pageName;
		this.title = title;
		this.listContent = listContent;
		this.subContent = subContent;
		this.location = location;
		this.filesDB = filesDB;
	}

	public Pages(Integer id, String pageName, String title, String listContent, String subContent, Set<FileDB> filesDB) {
		super();
		this.id = id;
		this.pageName = pageName;
		this.title = title;
		this.listContent = listContent;
		this.subContent = subContent;
		this.filesDB = filesDB;
	}

	public Pages(String pageName, String title, String listContent, String subContent) {
		super();
		this.pageName = pageName;
		this.title = title;
		this.listContent = listContent;
		this.subContent = subContent;
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

	public Set<FileDB> getFilesDB() {
		return filesDB;
	}

	public void setFilesDB(Set<FileDB> filesDB) {
		this.filesDB = filesDB;
	}

	@Override
	public String toString() {
		return "Pages [id=" + id + ", pageName=" + pageName + ", title=" + title + ", listContent=" + listContent
				+ ", subContent=" + subContent + ", filesDB=" + filesDB + "]";
	}

}
