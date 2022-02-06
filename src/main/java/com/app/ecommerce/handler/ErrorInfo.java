package com.app.ecommerce.handler;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorInfo {

    @JsonProperty("message")
    private String message;

    @JsonProperty("status_code")
    private Integer statusCode;

    @JsonProperty("uri")
    private String uriRequested;
}
