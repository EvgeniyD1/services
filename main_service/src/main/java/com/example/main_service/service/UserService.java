package com.example.main_service.service;

import com.example.main_service.domain.Role;
import com.example.main_service.domain.User;
import com.example.main_service.mapper.UserMapper;
import com.example.main_service.request.SignRequest;
import com.example.main_service.request.UserEmailUpdateRequest;
import com.example.main_service.request.UserRoleUpdateRequest;
import com.example.main_service.response.UserPageResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public User findUserByUsername(String username) {
        return userMapper.findUserByUsername(username.toLowerCase());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findUserByUsername(username.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public UserPageResponse getUsersWithPagination(int page, int size) {
        int offset = page * size;
        List<User> users = userMapper.getUsersWithPagination(offset, size);
        int userCount = userMapper.getTotalUserCount();
        int totalPages = (userCount + size - 1) / size;
        return new UserPageResponse(users, totalPages, page, size);
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public void insertUser(SignRequest request) {
        User user = new User();
        user.setUsername(request.getUsername().toLowerCase());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRegistrationDate(new Timestamp(new Date().getTime()));
        user.setRole(Role.USER);
        userMapper.insertUser(user);
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public User updateUserEmail(String username, UserEmailUpdateRequest request) {
        User userFromDb = userMapper.findUserByUsername(username.toLowerCase());
        userFromDb.setEmail(request.getEmail());
        userMapper.updateUserEmail(username.toLowerCase(), userFromDb);
        return userFromDb;
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public User updateUserRole(String username, UserRoleUpdateRequest request){
        User userFromDb = userMapper.findUserByUsername(username.toLowerCase());
        userFromDb.setRole(Role.valueOf(request.getRole()));
        userMapper.updateUserRole(username.toLowerCase(), userFromDb);
        return userFromDb;
    }

    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public int deleteUser(String username) {
        return userMapper.deleteUser(username.toLowerCase());
    }

    public User findUserByUsernameWithArticles(String username){
        return userMapper.findUserByUsernameWithArticles(username.toLowerCase());
    }

    public UserPageResponse getUsersWithArticlesAndPagination(int page, int size) {
        List<User> users = userMapper.getUsersWithArticlesAndPagination(page * size, size);
        int userCount = userMapper.getTotalUserCount();
        int totalPages = (userCount + size - 1) / size;
        return new UserPageResponse(users, totalPages, page, size);
    }
}
