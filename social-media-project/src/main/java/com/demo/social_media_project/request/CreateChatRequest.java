package com.demo.social_media_project.request;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class CreateChatRequest {
    private Integer userId;
}
