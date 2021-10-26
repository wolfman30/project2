package com.learning.project2.web.lex;

import com.learning.project2.web.lex.models.Interaction;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.lexruntimev2.LexRuntimeV2Client;
import software.amazon.awssdk.services.lexruntimev2.model.Message;
import software.amazon.awssdk.services.lexruntimev2.model.RecognizeTextRequest;
import software.amazon.awssdk.services.lexruntimev2.model.RecognizeTextResponse;
import software.amazon.awssdk.services.lexruntimev2.model.Slot;
import com.learning.project2.web.test.repositories.TestHistoryAnswerGivenRepository;

import javax.annotation.PostConstruct;

import java.util.Map;
import java.util.UUID;

import static com.learning.project2.web.lex.LexTest.getRecognizeTextRequest;

@Service
@Slf4j
public class BotService {

    @Value("${aws.lex.key}")
    private String key;

    @Value("${aws.lex.secret}")
    private  String secret;

    @Value("${aws.lex.localeId}")
    private String localeId;

    @Value("${aws.lex.botId}")
    private  String botId;

    @Value("${aws.lex.botAliasId}")
    private String botAliasId;

    private final Region region = Region.US_WEST_2;

    private AwsBasicCredentials awsCreds;
    private AwsCredentialsProvider awsCredentialsProvider;
    private LexRuntimeV2Client lexV2Client;

    @PostConstruct
    public void init(){
        log.info("Initializing bot from yaml file");
        try {
            awsCreds = AwsBasicCredentials.create(key, secret);
            awsCredentialsProvider = StaticCredentialsProvider.create(awsCreds);
            lexV2Client = LexRuntimeV2Client
                    .builder()
                    .credentialsProvider(awsCredentialsProvider)
                    .region(region)
                    .build();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public Interaction converse(Interaction interaction){

        Interaction sendToBot = response(interaction);

        if(sendToBot==null){
            return null;
        }

        if(sendToBot.getState().equals("ReadyForFulfillment")){
            return fulfillmentSwitchBoard(interaction);
        }

        return sendToBot;
    }

    private Interaction fulfillmentSwitchBoard(Interaction interaction) {

        switch(interaction.getIntent()){
            case("TellJoke"):
                return tellJoke(interaction);
            case("UserAsksHowAreYou"):
                return howAreYou(interaction);
            case("GetAverage"):
                return getAverage(interaction);
            default:
                return interaction;
        }

    }

    private Interaction howAreYou(Interaction interaction) {
        String feeling = interaction.getSlotValue("UserFeeling");
        if(feeling.equals("Good")){
            interaction.addToBotMessages("I'm glad to hear that!");
        }else if(feeling.equals("Bad")) {
            interaction.addToBotMessages("I'm sorry to hear that.");
        }else{
            interaction.addToBotMessages("Okay");
        }
        return interaction;
    }

    private Interaction tellJoke(Interaction interaction) {
        interaction.addToBotMessages("[insert joke text here]");
        return interaction;
    }

    private Interaction getAverage(Interaction interaction)
    {
        int average;

        interaction.addToBotMessages("Here is your average: " + average);
        return interaction;
    }

    private Interaction response(Interaction interaction) {
        try{
            // Assign a sessionID to the interaction if one has not yet been created
            if(interaction.getSessionId()==null){
                interaction.setSessionId(UUID.randomUUID().toString());
            }

            // Send request to bot
            RecognizeTextRequest recognizeTextRequest =
                    getRecognizeTextRequest(botId, botAliasId, localeId, interaction.getSessionId(), interaction.getCurrentUserMessage());
            RecognizeTextResponse response = lexV2Client.recognizeText(recognizeTextRequest);

            // Log bot response
            log.info("Request sent to bot with id: "+interaction.getSessionId());
            log.info(response.toString());

            // Add bot's response to the interaction
            for(Message m : response.messages()){
                interaction.addToBotMessages(m.content());
            }

            // Add slots to the interaction
            Map<String, Slot> slots = response.sessionState().intent().slots();
            for(String s : slots.keySet()){
                Slot slot = slots.get(s);
                log.info("Found slot value: "+s);
                if(slot!=null) {
                    log.info("Adding slot " + s + " with value " + slot.value().interpretedValue());
                    interaction.addToSlots(s, slot.value().interpretedValue());
                }
                else {
                    log.info("Indeterminate data. Adding null to slot "+ slot);
                    interaction.addToSlots(s, null);
                }
            }

            // Add Intent to the interaction
            interaction.setIntent(response.sessionState().intent().name());

            //Add State
            interaction.setState(response.sessionState().intent().state().toString());

            return interaction;


        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }


    public boolean isInitialized(){
        return (awsCredentialsProvider!=null||awsCreds!=null||lexV2Client!=null);
    }
}
