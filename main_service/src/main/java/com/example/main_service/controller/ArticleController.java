package com.example.main_service.controller;

import com.example.main_service.config.JwtTokenUtil;
import com.example.main_service.domain.Article;
import com.example.main_service.request.ArticleRequest;
import com.example.main_service.responce.ArticlePageRequest;
import com.example.main_service.service.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/articles")
@AllArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final JwtTokenUtil jwtTokenUtil;


    @Operation(summary = "find article by id", description = "returns article by id", responses = {
            @ApiResponse(responseCode = "200", description = "Success!!!"),
    })
    @Parameter(name = "id", description = "article id to search",
            in = ParameterIn.PATH, required = true, schema = @Schema(type = "string", defaultValue = "1"))
    @GetMapping("{id}")
    public ResponseEntity<Article> findArticleById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(articleService.findArticleById(id));
    }


    @Operation(summary = "find article by title", description = "returns article by title like", responses = {
            @ApiResponse(responseCode = "200", description = "Success!!!"),
    })
    @Parameter(name = "title", description = "article title to search",
            in = ParameterIn.PATH, required = true, schema = @Schema(type = "string", defaultValue = "text"))
    @GetMapping("/byTitle/{title}")
    public ResponseEntity<List<Article>> findArticleByTitle(@PathVariable("title") String title) {
        return ResponseEntity.ok(articleService.findArticlesByTitle(title));
    }


    @Operation(summary = "find article by user id", description = "returns article by userId", responses = {
            @ApiResponse(responseCode = "200", description = "Success!!!"),
    })
    @Parameter(name = "userId", description = "article title to search",
            in = ParameterIn.PATH, required = true, schema = @Schema(type = "string", defaultValue = "1"))
    @GetMapping("/byUserId/{userId}")
    public ResponseEntity<List<Article>> findArticlesByUserId(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(articleService.findArticlesByUserId(userId));
    }


    @Operation(summary = "create ne article",
            description = "write title and context of your article", responses = {
            @ApiResponse(responseCode = "200", description = "Success!!!"),
    })
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAuthority('USER') or hasAnyAuthority('MODERATOR') or hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<?> insertArticle(
            @RequestHeader("Authorization") @Parameter(hidden = true) String authorization,
            @RequestBody ArticleRequest request) {
        String token = authorization.substring("Bearer ".length());
        if (jwtTokenUtil.validateToken(token)) {
            articleService.insertArticle(Long.valueOf(jwtTokenUtil.getIdFromToken(token)), request);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok("Article created");
    }


    @Operation(summary = "article update",
            description = "returns updated article", responses = {
            @ApiResponse(responseCode = "200", description = "Success!!!"),
    })
    @Parameter(name = "id", description = "article id to update",
            in = ParameterIn.PATH, required = true, schema = @Schema(type = "string", defaultValue = "7"))
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAuthority('USER') or hasAnyAuthority('MODERATOR') or hasAuthority('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<?> updateArticle(
            @RequestHeader("Authorization") @Parameter(hidden = true) String authorization,
            @PathVariable("id") Long id,
            @RequestBody ArticleRequest request) {
        Article articleFromDb = articleService.findArticleById(id);
        String token = authorization.substring("Bearer ".length());
        if (jwtTokenUtil.validateToken(token)
                && Long.valueOf(jwtTokenUtil.getIdFromToken(token)).equals(articleFromDb.getUserId())) {
            return ResponseEntity.ok(articleService.updateArticle(articleFromDb, request));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


    @Operation(summary = "delete article",
            description = "delete by article id", responses = {
            @ApiResponse(responseCode = "200", description = "Success!!!"),
    })
    @Parameter(name = "id", description = "article id to delete",
            in = ParameterIn.PATH, required = true, schema = @Schema(type = "string", defaultValue = "6"))
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteArticle(@PathVariable("id") Long id) {
        articleService.deleteArticle(id);
        return ResponseEntity.ok("Article with id " + id + " was deleted");
    }


    @Operation(summary = "find articles with pagination",
            description = "returns articls page", responses = {
            @ApiResponse(responseCode = "200", description = "Success!!!"),
    })
    @Parameter(name = "page", description = "page number",
            in = ParameterIn.QUERY, schema = @Schema(type = "int", defaultValue = "0"))
    @Parameter(name = "size", description = "page length",
            in = ParameterIn.QUERY, schema = @Schema(type = "int", defaultValue = "5"))
    @GetMapping("/withPagination")
    public ResponseEntity<ArticlePageRequest> findArticlesWithPagination(@RequestParam(defaultValue = "0") int page,
                                                                         @RequestParam(defaultValue = "5") int size){
        return ResponseEntity.ok(articleService.findArticlesWithPagination(page, size));
    }


    @Operation(summary = "find article with author by article id", description = "returns full article by id",
            responses = {
            @ApiResponse(responseCode = "200", description = "Success!!!"),
    })
    @Parameter(name = "id", description = "article id to search",
            in = ParameterIn.PATH, required = true, schema = @Schema(type = "string", defaultValue = "1"))
    @GetMapping("{id}/withAuthor")
    public ResponseEntity<Article> findArticleByIdWithAuthor(@PathVariable("id") Long id){
        return ResponseEntity.ok(articleService.findArticleByIdWithAuthor(id));
    }


    @Operation(summary = "find article by title with author", description = "returns article by title with author like",
            responses = {
            @ApiResponse(responseCode = "200", description = "Success!!!"),
    })
    @Parameter(name = "title", description = "article title to search",
            in = ParameterIn.PATH, required = true, schema = @Schema(type = "string", defaultValue = "text"))
    @GetMapping("/byTitleWithAuthor/{title}")
    public ResponseEntity<List<Article>> findArticleByTitleWithAuthor(@PathVariable("title") String title){
        return ResponseEntity.ok(articleService.findArticlesByTitleWithAuthor(title));
    }
}
