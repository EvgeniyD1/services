package com.example.search_service.repository;

import com.example.search_service.domain.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends ElasticsearchRepository<User, Long> {

    List<User> findAllByUsername(String username);
}
