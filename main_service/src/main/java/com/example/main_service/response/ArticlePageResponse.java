package com.example.main_service.response;

import com.example.main_service.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class ArticlePageResponse implements Serializable {

    private List<Article> articles;
    private int totalPages;
    private int page;
    private int size;
}
