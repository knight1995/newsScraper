package com.news.scraper.entity;

import org.apache.solr.client.solrj.beans.Field;

public class Author {

  @Field
  private String author;

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

}
