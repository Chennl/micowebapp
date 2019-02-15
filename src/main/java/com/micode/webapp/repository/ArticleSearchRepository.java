package com.micode.webapp.repository;

import java.util.List;

 

import org.springframework.data.jpa.repository.JpaRepository;

 
import com.micode.webapp.entity.ArticleSearch;

public interface ArticleSearchRepository extends JpaRepository<ArticleSearch, Integer>{

	public List<ArticleSearch> findByKeywordLike(String keyword);
}
