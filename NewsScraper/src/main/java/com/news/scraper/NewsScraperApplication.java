package com.news.scraper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.news.scraper" })
public class NewsScraperApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsScraperApplication.class, args);
	}

}

