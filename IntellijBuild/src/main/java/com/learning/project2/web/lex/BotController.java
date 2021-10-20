package com.learning.project2.web.lex;


import com.learning.project2.web.lex.models.UserBotInteraction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/bot")
public class BotController {

    private BotService botService;

    @Autowired
    private void setBotService(BotService botService) {
        this.botService = botService;
    }

    @PostMapping(
            value = {"converse/{sessionId}", "converse"},
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> converse(@RequestBody UserBotInteraction response, @PathVariable(required = false) String sessionId) {

        String resp;
        if (sessionId == null) {
            resp = botService.converse(response.getUserInput());
        } else {
            resp = botService.converse(response.getUserInput(), sessionId);
        }

        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
}

