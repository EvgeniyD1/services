package com.example.main_service.mapper;

import com.example.main_service.domain.Article;
import com.example.main_service.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface ArticleMapper {

    @Select("select * from article where id = #{id}")
    Article findArticleById(@Param("id") Long id);

    @Select("select * from article where title like #{title}")
    List<Article> findArticlesByTitle(@Param("title") String title);

    @Select("select * from article where user_id = #{userId}")
    List<Article> findArticlesByUserId(@Param("userId") Long userId);

    @Insert("insert into article (user_id, title, context, creation_date) " +
            "values (#{userId}, #{title}, #{context}, #{creationDate})")
    void insertArticle(Article article);

    @Update("update article set title = #{article.title}, context = #{article.context} where id = #{id}")
    void updateArticle(@Param("id") Long id, @Param("article") Article article);

    @Delete("delete from article where id = #{id}")
    void deleteArticle(@Param("id") Long id);

    @Select("select * from article limit #{limit} offset #{offset}")
    List<Article> findArticlesWithPagination(@Param("offset") int offset, @Param("limit") int limit);

    /*pagination helper method, returns the total number of articles*/
    @Select("SELECT COUNT(*) FROM article")
    int getTotalArticlesCount();

    @Select("select * from article where id = #{id}")
    @Results(value = {
            @Result(property = "user", column = "user_id", one = @One(select = "getUserById"))
    })
    Article findArticleByIdWithAuthor(@Param("id") Long id);

    /*findArticleByIdWithAuthor and findArticlesByTitleWithAuthor helper method*/
    @Select("select * from users where id = #{userId}")
    User getUserById(int userId);

    /*need fix n+1, maybe better create additional response with username*/
    @Select("select * from article where title like #{title}")
    @Results(value = {
            @Result(property = "user", column = "user_id", one = @One(select = "getUserById", fetchType = FetchType.EAGER))
    })
    List<Article> findArticlesByTitleWithAuthor(@Param("title") String title);
}
