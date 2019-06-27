package com.news.scraper.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.stereotype.Component;

import com.news.scraper.constants.SearchConstants;

@Component
public class QueryBuilderImpl implements QueryBuilder {

	@Override
	public SolrQuery buildsolrQuery(Map<String, String> queryParams) {
		SolrQuery query = new SolrQuery();
		for (Map.Entry<String, String> entry : queryParams.entrySet()) {
			query.set(entry.getKey(), entry.getValue());
		}
		return query;
	}

	@Override
	public SolrQuery buildArticleQuery(String queryString, Long pageNumber) {
		Map<String, String> map = new HashMap<>();
		map.put("q", queryString);
		map.put("rows", SearchConstants.MAX_RESULTS);
		map.put("sort", "score desc");
		Long start = pageNumber * Long.parseLong(SearchConstants.MAX_RESULTS);
		map.put("start", start.toString());
		return buildsolrQuery(map);
	}

}
