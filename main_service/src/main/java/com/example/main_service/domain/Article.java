package com.example.main_service.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Article implements Serializable {

    private Long id;
    private Long userId;
    private String title;
    private String content;
    private Date creationDate;
}
