package com.example.main_service.controller;

import com.example.main_service.config.JwtTokenUtil;
import com.example.main_service.request.SignRequest;
import com.example.main_service.response.JwtResponse;
import com.example.main_service.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class RegistrationController {

    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    @Operation(summary = "registration form", description = "write your details here", responses = {
            @ApiResponse(responseCode = "200", description = "Success!!!"),
    })
    @PostMapping("/registration")
    public ResponseEntity<String> createAccount(@RequestBody SignRequest request){
        if (userService.findUserByUsername(request.getUsername())!=null){
            return ResponseEntity.badRequest().body("A user with the same name already exists!");
        }
        userService.insertUser(request);
        return ResponseEntity.ok("Successful registration!");
    }

    @Operation(summary = "login form", description = "write your details here, and receive jwt token", responses = {
            @ApiResponse(responseCode = "200", description = "Success!!!"),
    })
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody SignRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername().toLowerCase(),
                        request.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenUtil.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        /*I have one role by user*/
        String role = userDetails.getAuthorities().stream().findFirst().orElseThrow().getAuthority();

        return ResponseEntity.ok(new JwtResponse(userDetails.getUsername(), role, token));
    }
}
