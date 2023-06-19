package com.example.search_service.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Data
@Document(indexName = "users")
public class User implements Serializable {

    @Id
    private Long id;

    @Field(type = FieldType.Text)
    private String username;

    @JsonIgnore
    @Field(type = FieldType.Text)
    private String password;

    @Field(type = FieldType.Text)
    private String email;

    @Field(type = FieldType.Date, name = "registration_date")
    private Timestamp registrationDate;

    @Field(type = FieldType.Keyword)
    private String role;

    @Field(type = FieldType.Nested)
    private List<Article> articles;
}
