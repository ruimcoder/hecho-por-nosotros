package com.folcamp.hechopornosotros.config.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {
    private int code;
    private String status;
    private String message;
    private String path;
}
