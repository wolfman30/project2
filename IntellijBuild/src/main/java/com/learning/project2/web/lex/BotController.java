package com.learning.project2.web.lex;


import com.learning.project2.web.WebLinks;
import com.learning.project2.web.lex.models.Interaction;
import com.learning.project2.web.lex.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = WebLinks.ANGULAR_ORIGIN)
@RestController
@RequestMapping(path = "/bot")
public class BotController {

    private BotService botService;

    @Autowired
    private void setBotService(BotService botService) {
        this.botService = botService;
    }

    @CrossOrigin(origins = WebLinks.ANGULAR_ORIGIN)
    @PostMapping(
            value = {"converse/", "converse"},
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Response> converse(@RequestBody Response input)
    {

        Interaction in = new Interaction();
        in.addToUserMessages(input.getMessage());
        in.setSessionId(input.getSessionId());
        in.setUserId(input.getUserId());

        Interaction ex;

        ex = botService.converse(in);

        if(ex!=null){
            Response response = new Response(in.getSessionId(), in.getCurrentBotMessage(), in.getUserId());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

