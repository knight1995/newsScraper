package com.news.scraper.constants;

public class SearchConstants {
	
	public static final String AUTHOR_QUERY_STRING = "author:(/.*%s.*/ OR %s)";
	public static final String TITLE_QUERY_STRING = "title:(/.*%s.*/ OR %s)";
	public static final String DESCRIPTION_QUERY_STRING = "description:(/.*%s.*/ OR %s)";
	public static final String MAX_RESULTS = "30";

}
