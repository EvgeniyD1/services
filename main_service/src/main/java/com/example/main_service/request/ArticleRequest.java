package com.example.main_service.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Schema(description = "Article request with title and context")
public class ArticleRequest {

    @Schema (requiredMode = Schema.RequiredMode.REQUIRED, type = "string",
            description = "write down your Article name", defaultValue = "some article")
    private String title;

    @Schema (requiredMode = Schema.RequiredMode.REQUIRED, type = "string",
            description = "write down your Context", defaultValue = "somme information about something")
    private String context;
}
