package com.example.search_service.controller;

import com.example.search_service.domain.User;
import com.example.search_service.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    @Operation(summary = "find users by username", description = "returns all users with username like", responses = {
            @ApiResponse(responseCode = "200", description = "Success!!!"),
    })
    @Parameter(name = "username", description = "username to search",
            in = ParameterIn.PATH, required = true, schema = @Schema(type = "string", defaultValue = "user"))
    @GetMapping("{username}")
    public ResponseEntity<?> findAllByUsername(@PathVariable("username") String username){
        List<User> users = userService.findAllByUsername(username);
        if (!users.isEmpty()){
            return ResponseEntity.ok(users);
        }
        return ResponseEntity.notFound().build();
    }
}
