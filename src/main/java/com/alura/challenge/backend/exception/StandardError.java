package com.alura.challenge.backend.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StandardError implements Serializable {

    @Serial
    private static final long serialVersionUID = -8346768838153267263L;

    private Long timeStamp;
    private Integer status;
    private String error;
    private List<DataValidationError> errors;
    private String message;
    private String path;
    private Map<String, Object> map;

    public StandardError(Long timeStamp, Integer status, String error, String path) {
        this.timeStamp = timeStamp;
        this.status = status;
        this.error = error;
        this.path = path;
    }

    public StandardError(Integer status, String message, String path) {
        this.timeStamp = System.currentTimeMillis();
        this.status = status;
        this.message = message;
        this.path = path;
    }
}