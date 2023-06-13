package com.example.main_service.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Schema(description = "Login request with login and password")
public class SignRequest {

    @Schema (requiredMode = Schema.RequiredMode.REQUIRED, type = "string",
            description = "write down your username", defaultValue = "user")
    private String username;
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED, type = "string",
            description = "create your password", defaultValue = "1")
    private String password;
}
