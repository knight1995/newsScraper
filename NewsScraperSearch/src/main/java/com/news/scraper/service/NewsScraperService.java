package com.news.scraper.service;

import org.springframework.http.ResponseEntity;

@SuppressWarnings("rawtypes")
public interface NewsScraperService {
	
	public ResponseEntity searchArticle(String author, String title, String description, Long pageNumber);
	
	public ResponseEntity searchAuthor(String author, Long pageNumber);

}
