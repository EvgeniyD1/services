package com.example.search_service.service;

import com.example.search_service.domain.Article;
import com.example.search_service.repository.ArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public List<Article> findAllByTitle(String title){
        return articleRepository.findAllByTitle(title);
    }

    public List<Article> findAllByContext(String context){
        return articleRepository.findAllByContext(context);
    }
}
