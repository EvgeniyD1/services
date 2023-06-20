package com.example.main_service.domain;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class Article implements Serializable {

    private Long id;
    private Long userId;
    private String title;
    private String context;
    private Timestamp createdAt;

    private User user;
}
