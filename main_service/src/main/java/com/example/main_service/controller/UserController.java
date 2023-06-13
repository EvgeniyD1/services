package com.example.main_service.controller;

import com.example.main_service.config.JwtTokenUtil;
import com.example.main_service.domain.User;
import com.example.main_service.request.UserEmailUpdateRequest;
import com.example.main_service.request.UserRoleUpdateRequest;
import com.example.main_service.responce.UserPage;
import com.example.main_service.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;

    @Operation(summary = "find users with pagination",
            description = "returns users page", responses = {
            @ApiResponse(responseCode = "200", description = "Success!!!"),
    })
    @Parameter(name = "page", description = "page number",
            in = ParameterIn.QUERY, schema = @Schema(type = "int", defaultValue = "0"))
    @Parameter(name = "size", description = "page length",
            in = ParameterIn.QUERY, schema = @Schema(type = "int", defaultValue = "5"))
    @GetMapping
    public ResponseEntity<UserPage> getUsersWithPagination(@RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(userService.getUsersWithPagination(page, size));
    }


    @Operation(summary = "find user by username", description = "returns user by username", responses = {
            @ApiResponse(responseCode = "200", description = "Success!!!"),
    })
    @Parameter(name = "username", description = "username to search",
            in = ParameterIn.PATH, required = true, schema = @Schema(type = "string", defaultValue = "user"))
    @GetMapping("{username}")
    public ResponseEntity<User> findUserByUsername(@PathVariable("username") String username) {
        return ResponseEntity.ok(userService.findUserByUsername(username));
    }


    @Operation(summary = "update user email",
            description = "returns updated user", responses = {
            @ApiResponse(responseCode = "200", description = "Success!!!"),
    })
    @Parameter(name = "username", description = "username to update",
            in = ParameterIn.PATH, required = true, schema = @Schema(type = "string", defaultValue = "user"))
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAuthority('USER') or hasAnyAuthority('MODERATOR') or hasAuthority('ADMIN')")
    @PutMapping("{username}/updateEmail")
    public ResponseEntity<?> updateUserEmail(@RequestHeader("Authorization") @Parameter(hidden = true) String authorization,
                                             @PathVariable("username") String username,
                                             @RequestBody UserEmailUpdateRequest request) {
        String token = authorization.substring("Bearer ".length());
        if (jwtTokenUtil.validateToken(token) && jwtTokenUtil.getUsernameFromToken(token).equals(username)) {
            return ResponseEntity.ok(userService.updateUserEmail(username, request));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


    @Operation(summary = "change user role",
            description = "returns updated user", responses = {
            @ApiResponse(responseCode = "200", description = "Success!!!"),
    })
    @Parameter(name = "username", description = "username to update",
            in = ParameterIn.PATH, required = true, schema = @Schema(type = "string", defaultValue = "user"))
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("{username}/updateRole")
    public ResponseEntity<User> updateUserRole(@PathVariable("username") String username,
                                               @RequestBody UserRoleUpdateRequest request) {
        return ResponseEntity.ok(userService.updateUserRole(username, request));
    }


    @Operation(summary = "delete user",
            description = "delete user by username", responses = {
            @ApiResponse(responseCode = "200", description = "Success!!!"),
    })
    @Parameter(name = "username", description = "username to delete",
            in = ParameterIn.PATH, required = true, schema = @Schema(type = "string", defaultValue = "user"))
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("{username}")
    public ResponseEntity<String> deleteUser(@PathVariable("username") String username) {
        userService.deleteUser(username);
        return ResponseEntity.ok("User with username \"" + username + "\" was deleted");
    }
}
