package com.example.main_service.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class JwtResponse implements Serializable {

    private String username;
    private String role;
    private String token;

}

