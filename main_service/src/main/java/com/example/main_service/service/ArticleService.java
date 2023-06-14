package com.example.main_service.service;

import com.example.main_service.domain.Article;
import com.example.main_service.mapper.ArticleMapper;
import com.example.main_service.request.ArticleRequest;
import com.example.main_service.responce.ArticlePageRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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

    public void insertArticle(Long userId, ArticleRequest request){
        Article article = new Article();
        article.setUserId(userId);
        article.setTitle(request.getTitle());
        article.setContext(request.getContext());
        article.setCreationDate(new Date());
        articleMapper.insertArticle(article);
    }

    public Article updateArticle(Article articleFromDb,ArticleRequest request){
        articleFromDb.setTitle(request.getTitle());
        articleFromDb.setContext(request.getContext());
        articleMapper.updateArticle(articleFromDb.getId(), articleFromDb);
        return articleFromDb;
    }

    public void deleteArticle(Long id){
        articleMapper.deleteArticle(id);
    }

    public ArticlePageRequest findArticlesWithPagination(int page, int size){
        List<Article> articles = articleMapper.findArticlesWithPagination(page * size, size);
        int userCount = articleMapper.getTotalArticlesCount();
        int totalPages = (userCount + size - 1) / size;
        return new ArticlePageRequest(articles, totalPages, page, size);
    }

    public Article findArticleByIdWithAuthor(Long id){
        return articleMapper.findArticleByIdWithAuthor(id);
    }

    public List<Article> findArticlesByTitleWithAuthor(String title){
        return articleMapper.findArticlesByTitleWithAuthor(title);
    }
}
