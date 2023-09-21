package com.example.search_service.service;

import com.example.search_service.domain.User;
import com.example.search_service.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAllByUsername(String username){
        return userRepository.findAllByUsername(username);
    }
}
