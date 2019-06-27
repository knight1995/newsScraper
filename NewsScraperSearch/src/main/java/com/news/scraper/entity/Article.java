package com.news.scraper.entity;

import org.apache.solr.client.solrj.beans.Field;

public class Article {

	@Field
	private String url;

	@Field
	private String title;

	@Field
	private String author;

	@Field
	private String description;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
