package com.example.main_service.mapper;

import com.example.main_service.domain.Article;
import com.example.main_service.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from users where username=#{username}")
    User findUserByUsername(@Param("username") String username);

    @Select("select * from users limit #{limit} offset #{offset}")
    List<User> getUsersWithPagination(@Param("offset") int offset, @Param("limit") int limit);

    /*pagination helper method, returns the total number of users*/
    @Select("SELECT COUNT(*) FROM users")
    int getTotalUserCount();

    @Insert("insert into users (username, password, email, registration_date, role) " +
            "VALUES (#{username}, #{password}, #{email}, #{registrationDate}, #{role})")
    void insertUser(User user);

    @Update("update users set email = #{user.email} where username = #{username}")
    void updateUserEmail(@Param("username") String username, @Param("user") User user);

    @Update("update users set role = #{user.role} where username = #{username}")
    void updateUserRole(@Param("username") String username, @Param("user") User user);

    @Delete("delete from users where username = #{username}")
    int deleteUser(@Param("username") String username);

    @Select("select * from users where username=#{username}")
    @Results(value = {
            /*column = "id" because it's necessary (it's article id)*/
            @Result(property = "articles", column = "id", javaType = List.class,
                    many = @Many(select = "selectArticles"))
    })
    User findUserByUsernameWithArticles(@Param("username") String username);

    /*helper method for findUserByUsernameWithArticles*/
    @Select("select * from article where user_id = #{userId}")
    List<Article> selectArticles(Long userId);

    /*n+1 fix, I think I can do better later*/
    @Select("select *, (select string_agg(article.id::text, ',') from article where article.user_id = users.id ) as " +
            "a_numbers from users limit #{limit} offset #{offset}")
    @Results(value = {
            /*a_numbers it's from sub select*/
            @Result(property = "articles", column = "a_numbers", javaType = List.class,
                    many = @Many(select = "selectArticlesForPagination"))
    })
    List<User> getUsersWithArticlesAndPagination(@Param("offset") int offset, @Param("limit") int limit);

    /*helper method for getUsersWithArticlesAndPagination*/
    @Select("select * from article where id = ANY(STRING_TO_ARRAY(#{aNumbers}, ',')::bigint[])")
    List<Article> selectArticlesForPagination(@Param("aNumbers") String aNumbers);
}
