package com.example.main_service.mapper;

import com.example.main_service.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
    void deleteUser(@Param("username") String username);
}
