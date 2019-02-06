package com.news.scraper.service;

import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;

public interface QueryBuilder {

	public SolrQuery buildsolrQuery(Map<String, String> queryParams);

	public SolrQuery buildArticleQuery(String queryString, Long pageNumber);

}
