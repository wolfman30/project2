package com.learning.project2.web.lex;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.lexruntimev2.LexRuntimeV2Client;
import software.amazon.awssdk.services.lexruntimev2.model.RecognizeTextRequest;
import software.amazon.awssdk.services.lexruntimev2.model.RecognizeTextResponse;

import javax.annotation.PostConstruct;

import java.util.UUID;

import static com.learning.project2.web.lex.LexTest.getRecognizeTextRequest;

@Service
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
        System.out.println("\n\n\n\n\n\n\n\n\n\n...");
        System.out.println("Initializing bot");
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

    public String converse(String userInput){
        return this.converse(userInput, UUID.randomUUID().toString());
    }

    public String converse(String userInput, String sessionId){

        if(!this.isInitialized()) init();

        RecognizeTextRequest recognizeTextRequest = getRecognizeTextRequest(botId, botAliasId, localeId, sessionId, userInput);
        RecognizeTextResponse recognizeTextResponse = lexV2Client.recognizeText(recognizeTextRequest);
        try {
            return recognizeTextResponse.messages().get(0).content();
        }catch(Exception e){
            e.printStackTrace();
            return "error";
        }
    }



    public boolean isInitialized(){
        return (awsCredentialsProvider!=null||awsCreds!=null||lexV2Client!=null);
    }
}
