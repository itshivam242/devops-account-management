package com.nagarro.accountmanagement.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
public class ErrorResponseDto {
    private HttpStatus status;
    private LocalDateTime timestamp;
    private String message;
}
