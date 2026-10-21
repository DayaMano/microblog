package com.microblog.user_service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ApiErrorResponse {
    private int status;
    private String message;
    private LocalDateTime timestamp;
}
