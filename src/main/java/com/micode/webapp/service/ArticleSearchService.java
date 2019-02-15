package com.micode.webapp.service;



import java.util.List;

import com.micode.webapp.entity.ArticleSearch;

public interface  ArticleSearchService {
	
  	List<ArticleSearch> getArticleSearchList();
  	ArticleSearch searchArticleByKeyword(String keyword);
  	String searchArticle(String keyword); 
 
}
