package com.example.main_service.responce;

import com.example.main_service.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class ArticlePageRequest implements Serializable {

    private List<Article> articles;
    private int totalPages;
    private int page;
    private int size;
}
