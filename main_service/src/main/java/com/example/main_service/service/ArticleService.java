package com.example.main_service.service;

import com.example.main_service.domain.Article;
import com.example.main_service.mapper.ArticleMapper;
import com.example.main_service.request.ArticleRequest;
import com.example.main_service.response.ArticlePageResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ArticleService {

    private final ArticleMapper articleMapper;

    public  Article findArticleById(Long id){
        return articleMapper.findArticleById(id);
    }

    public List<Article> findArticlesByTitle(String title){
        return articleMapper.findArticlesByTitle(title);
    }

    public List<Article> findArticlesByUserId(Long userId){
        return articleMapper.findArticlesByUserId(userId);
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public void insertArticle(Long userId, ArticleRequest request){
        Article article = new Article();
        article.setUserId(userId);
        article.setTitle(request.getTitle());
        article.setContext(request.getContext());
        article.setCreatedAt(new Timestamp(new Date().getTime()));
        articleMapper.insertArticle(article);
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public Article updateArticle(Article articleFromDb,ArticleRequest request){
        articleFromDb.setTitle(request.getTitle());
        articleFromDb.setContext(request.getContext());
        articleMapper.updateArticle(articleFromDb.getId(), articleFromDb);
        return articleFromDb;
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public int deleteArticle(Long id){
        return articleMapper.deleteArticle(id);
    }

    public ArticlePageResponse findArticlesWithPagination(int page, int size){
        List<Article> articles = articleMapper.findArticlesWithPagination(page * size, size);
        int userCount = articleMapper.getTotalArticlesCount();
        int totalPages = (userCount + size - 1) / size;
        return new ArticlePageResponse(articles, totalPages, page, size);
    }

    public Article findArticleByIdWithAuthor(Long id){
        return articleMapper.findArticleByIdWithAuthor(id);
    }

    public List<Article> findArticlesByTitleWithAuthor(String title){
        return articleMapper.findArticlesByTitleWithAuthor(title);
    }
}
