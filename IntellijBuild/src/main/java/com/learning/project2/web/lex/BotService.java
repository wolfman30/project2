package com.learning.project2.web.lex;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BotService {

    @Value("${aws.lex.key}")
    private static String key;

    @Value("${aws.lex.secret}")
    private static String secret;

}
