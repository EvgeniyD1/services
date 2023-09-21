package com.example.search_service.controller;

import com.example.search_service.domain.Article;
import com.example.search_service.service.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("articles")
public class ArticleController {

    private final ArticleService articleService;


    @Operation(summary = "find articles by title", description = "returns all articles with title like", responses = {
            @ApiResponse(responseCode = "200", description = "Success!!!"),
    })
    @Parameter(name = "title", description = "title to search",
            in = ParameterIn.PATH, required = true, schema = @Schema(type = "string", defaultValue = "article"))
    @GetMapping("byTitle/{title}")
    public ResponseEntity<?> findAllByTitle(@PathVariable("title") String title){
        List<Article> articles = articleService.findAllByTitle(title);
        if (!articles.isEmpty()){
            return ResponseEntity.ok(articles);
        }
        return ResponseEntity.notFound().build();
    }


    @Operation(summary = "find articles by context", description = "returns all articles with context like", responses = {
            @ApiResponse(responseCode = "200", description = "Success!!!"),
    })
    @Parameter(name = "context", description = "context to search",
            in = ParameterIn.PATH, required = true, schema = @Schema(type = "string", defaultValue = "information"))
    @GetMapping("byContext/{context}")
    public ResponseEntity<?> findAllByContext(@PathVariable("context") String context){
        List<Article> articles = articleService.findAllByContext(context);
        if (!articles.isEmpty()){
            return ResponseEntity.ok(articles);
        }
        return ResponseEntity.notFound().build();
    }
}
