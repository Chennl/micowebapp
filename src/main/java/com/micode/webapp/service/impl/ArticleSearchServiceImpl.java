package com.micode.webapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micode.webapp.entity.ArticleSearch;
import com.micode.webapp.repository.ArticleSearchRepository;
import com.micode.webapp.service.ArticleSearchService;


@Service
public class ArticleSearchServiceImpl implements ArticleSearchService {
	@Autowired
	ArticleSearchRepository articleSearchRepository;

	@Override
	public List<ArticleSearch> getArticleSearchList() {
		return articleSearchRepository.findAll();
	}

	@Override
	public ArticleSearch searchArticleByKeyword(String keyword) {
		
		List<ArticleSearch> list =articleSearchRepository.findByKeywordLike("%"+keyword+"%");
 		return (list.size()>0)? list.get(0):null;
	}

	@Override
	public String searchArticle(String keyword) {
		ArticleSearch article =searchArticleByKeyword(keyword);
		return article==null?"":article.getArticleNo();
	}

	

	

}
