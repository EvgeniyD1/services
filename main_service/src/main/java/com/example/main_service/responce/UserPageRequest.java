package com.example.main_service.responce;

import com.example.main_service.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class UserPageRequest implements Serializable {

    private List<User> users;
    private int totalPages;
    private int page;
    private int size;

}
