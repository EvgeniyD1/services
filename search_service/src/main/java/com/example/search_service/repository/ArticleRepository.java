package com.example.search_service.repository;

import com.example.search_service.domain.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends ElasticsearchRepository<Article, Long> {

    List<Article> findAllByTitle(String title);

    List<Article> findAllByContext(String context);
}
