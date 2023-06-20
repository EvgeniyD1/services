package com.example.main_service.response;

import com.example.main_service.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class UserPageResponse implements Serializable {

    private List<User> users;
    private int totalPages;
    private int page;
    private int size;

}
