package com.learning.project2.web.lex.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {

    private String sessionId;
    private String message;
    private Long userId;

}
