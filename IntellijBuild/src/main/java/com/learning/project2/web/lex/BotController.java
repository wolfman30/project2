package com.learning.project2.web.lex;


import com.learning.project2.web.WebLinks;
import com.learning.project2.web.lex.models.Interaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/bot")
@CrossOrigin(origins = WebLinks.ANGULAR_ORIGIN)
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
    public ResponseEntity<Interaction> converse(@RequestBody Interaction exchange) {

        Interaction ex;
        ex = botService.converse(exchange);

        if(ex!=null){
            return new ResponseEntity<>(ex, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

