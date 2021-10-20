package com.learning.project2.lex;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.lexruntimev2.LexRuntimeV2Client;
import software.amazon.awssdk.services.lexruntimev2.model.RecognizeTextRequest;
import software.amazon.awssdk.services.lexruntimev2.model.RecognizeTextResponse;

import java.net.URISyntaxException;
import java.util.UUID;


/**
 * This is a sample application to interact with a bot using RecognizeText API.
 */
@Service
public class LexTest {

    public static void main(String[] args) throws URISyntaxException, InterruptedException {
        String botId = "PMRFRTC6SE";
        String botAliasId = "TSTALIASID";
        String localeId = "en_US";
        String accessKey = "AKIAQDWPQ6STVN5OEIV5";
        String secretKey = "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";
        String sessionId = UUID.randomUUID().toString();
        Region region = Region.US_WEST_2; // pick an appropriate region

        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(accessKey, secretKey);
        AwsCredentialsProvider awsCredentialsProvider = StaticCredentialsProvider.create(awsCreds);

        LexRuntimeV2Client lexV2Client = LexRuntimeV2Client
                .builder()
                .credentialsProvider(awsCredentialsProvider)
                .region(region)
                .build();

        // utterance 1
        String userInput = "Say Hello World";
        RecognizeTextRequest recognizeTextRequest = getRecognizeTextRequest(botId, botAliasId, localeId, sessionId, userInput);
        RecognizeTextResponse recognizeTextResponse = lexV2Client.recognizeText(recognizeTextRequest);

        System.out.println("User : " + userInput);
        recognizeTextResponse.messages().forEach(message -> {
            System.out.println("Bot : " + message.content());
        });
//
//        // utterance 1
//        userInput = "Can I hear a joke?";
//        recognizeTextRequest = getRecognizeTextRequest(botId, botAliasId, localeId, sessionId, userInput);
//        recognizeTextResponse = lexV2Client.recognizeText(recognizeTextRequest);
//
//        System.out.println("User : " + userInput);
//        recognizeTextResponse.messages().forEach(message -> {
//            System.out.println("Bot : " + message.content());
//        });

//        // utterance 2
//        userInput = "1 dozen roses";
//        recognizeTextRequest = getRecognizeTextRequest(botId, botAliasId, localeId, sessionId, userInput);
//        recognizeTextResponse = lexV2Client.recognizeText(recognizeTextRequest);
//
//        System.out.println("User : " + userInput);
//        recognizeTextResponse.messages().forEach(message -> {
//            System.out.println("Bot : " + message.content());
//        });

//        // utterance 3
//        userInput = "next monday";
//        recognizeTextRequest = getRecognizeTextRequest(botId, botAliasId, localeId, sessionId, userInput);
//        recognizeTextResponse = lexV2Client.recognizeText(recognizeTextRequest);
//
//        System.out.println("User : " + userInput);
//        recognizeTextResponse.messages().forEach(message -> {
//            System.out.println("Bot : " + message.content());
//        });
//
//        // utterance 4
//        userInput = "5 in evening";
//        recognizeTextRequest = getRecognizeTextRequest(botId, botAliasId, localeId, sessionId, userInput);
//        recognizeTextResponse = lexV2Client.recognizeText(recognizeTextRequest);
//
//        System.out.println("User : " + userInput);
//        recognizeTextResponse.messages().forEach(message -> {
//            System.out.println("Bot : " + message.content());
//        });
//
//        // utterance 5
//        userInput = "Yes";
//        recognizeTextRequest = getRecognizeTextRequest(botId, botAliasId, localeId, sessionId, userInput);
//        recognizeTextResponse = lexV2Client.recognizeText(recognizeTextRequest);
//
//        System.out.println("User : " + userInput);
//        recognizeTextResponse.messages().forEach(message -> {
//            System.out.println("Bot : " + message.content());
//        });
    }

    private static RecognizeTextRequest getRecognizeTextRequest(String botId, String botAliasId, String localeId, String sessionId, String userInput) {
        RecognizeTextRequest recognizeTextRequest = RecognizeTextRequest.builder()
                .botAliasId(botAliasId)
                .botId(botId)
                .localeId(localeId)
                .sessionId(sessionId)
                .text(userInput)
                .build();
        return recognizeTextRequest;
    }
}