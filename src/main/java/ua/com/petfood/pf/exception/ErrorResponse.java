package ua.com.petfood.pf.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
public class ErrorResponse {
    private HttpStatus status;
    private String timestamp;
    private List<ErrorDetails> errors;
    private String path;

    @Data
    public static class ErrorDetails {
        private String fieldName;
        private String message;
    }
}