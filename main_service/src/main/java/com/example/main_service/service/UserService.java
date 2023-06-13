package com.example.main_service.service;

import com.example.main_service.domain.Role;
import com.example.main_service.domain.User;
import com.example.main_service.mapper.UserMapper;
import com.example.main_service.request.SignRequest;
import com.example.main_service.request.UserEmailUpdateRequest;
import com.example.main_service.request.UserRoleUpdateRequest;
import com.example.main_service.responce.UserPage;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public User findUserByUsername(String username) {
        return userMapper.findUserByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public UserPage getUsersWithPagination(int page, int size) {
        int offset = page * size;
        List<User> users = userMapper.getUsersWithPagination(offset, size);
        int userCount = userMapper.getTotalUserCount();
        int totalPages = (userCount + size - 1) / size;
        return new UserPage(users, totalPages, page, size);
    }

    public void insertUser(SignRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRegistrationDate(new Date());
        user.setRole(Role.USER);
        userMapper.insertUser(user);
    }

    public User updateUserEmail(String username, UserEmailUpdateRequest request) {
        User userFromDb = userMapper.findUserByUsername(username);
        userFromDb.setEmail(request.getEmail());
        userMapper.updateUserEmail(username, userFromDb);
        return userFromDb;
    }

    public User updateUserRole(String username, UserRoleUpdateRequest request){
        User userFromDb = userMapper.findUserByUsername(username);
        userFromDb.setRole(Role.valueOf(request.getRole()));
        userMapper.updateUserRole(username, userFromDb);
        return userFromDb;
    }

    public void deleteUser(String username) {
        userMapper.deleteUser(username);
    }

}
