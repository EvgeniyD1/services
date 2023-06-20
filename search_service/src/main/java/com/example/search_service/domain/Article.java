package com.example.search_service.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

@Data
@Document(indexName = "dockerdb.public.article")
public class Article implements Serializable {

    @Id
    private Long id;

    @Field(type = FieldType.Long, name = "user_id")
    private Long userId;

    @Field(type = FieldType.Text)
    private String title;

    @Field(type = FieldType.Text)
    private String context;

    @Field(type = FieldType.Date, name = "created_at")
    private Date createdAt;

//    @Field(type = FieldType.Object)
//    private User user;
}
