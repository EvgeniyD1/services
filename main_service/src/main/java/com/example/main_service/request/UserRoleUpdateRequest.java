package com.example.main_service.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Schema(description = "used to update the user's role")
public class UserRoleUpdateRequest {

    @Schema (requiredMode = Schema.RequiredMode.REQUIRED, type = "string",
            description = "chose between ADMIN, MODERATOR, USER", defaultValue = "ADMIN")
    private String role;
}
