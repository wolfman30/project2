package com.learning.project2.web.lex;

import com.learning.project2.web.lex.models.Interaction;
import com.learning.project2.web.test.models.history.TestHistory;
import com.learning.project2.web.test.repositories.TestHistoryRepository;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

import javax.annotation.PostConstruct;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
@NoArgsConstructor
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

    private TestHistoryRepository testHistoryRepository;

    @Autowired
    private void setTestHistoryRepository(TestHistoryRepository testHistoryRepository){
        this.testHistoryRepository = testHistoryRepository;
    }

    public BotService(TestHistoryRepository thr){
        this();
        this.testHistoryRepository = thr;
    }

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
            case("HowAreYouResponse"):
                return respondToHowAreYou(interaction);
            case("HowAmIDoing"):
                return getAverage(interaction);
            case("Spring"):
                return tellMeAboutSpring(interaction);
            case("IoC"):
                return tellMeAboutIoC(interaction);
            case("Hibernate"):
                return tellMeAboutHibernate(interaction);
            case("HibernateAnnotations"):
                return tellMeAboutHibernateAnnotations(interaction);
            case("SpringVsHibernate"):
                return explainDiffBetweenHibernateSpring(interaction);
            case("LifeMeaning"):
                return explainMeaningOfLife(interaction);
            default:
                return interaction;
        }

    }

    private Interaction explainMeaningOfLife(Interaction interaction)
    {
        interaction.addToBotMessages("Ultimately: none. For now: pass your QCs and assessments, suffer the intense learning now, and live the rest of your life as a creator of great applications that can solve really cool problems.");
        return interaction;
    }

    private Interaction tellMeAboutHibernateAnnotations(Interaction interaction)
    {
        interaction.addToBotMessages("Hibernate annotations start with the @ symbol and provide metadata to map Java objects to relational database tables which is commonly used to replace or in addition to an XML file. One of the most commonly used annotations in Hibernate is @Entity placed over a Java class to indicate it maps to an entity in the relational database.");
        return interaction;
    }

    private Interaction explainDiffBetweenHibernateSpring(Interaction interaction)
    {
        interaction.addToBotMessages("Spring is a larger framework than Hibernate. Developers may or may not use Hibernate with Spring. Developers could use Spring ORM instead of Hibernate. Hibernate just takes care of mapping java objects to reltional database tables whereas Spring provides dependency injection, aspect-oriented programming, web frameworks for easier web development, and more to reduce the complexity of developing enterprise-level applications. Enterprise developers would choose Spring over Hibernate to develop large-scale enterprise applications if they had to choose.");
        return interaction;
    }

    private Interaction tellMeAboutHibernate(Interaction interaction)
    {
        interaction.addToBotMessages("Hibernate is an object-relational mapping (ORM) tool used to convert Java objects to database tables, allowing us to interact with a relational database without the required use of SQL. It is one of the most popular ORM frameworks in use today and is a standard implementation of Java Persistence API (JPA).");
        return interaction;
    }
    private Interaction respondToHowAreYou(Interaction interaction)
    {
        interaction.addToBotMessages("Good news! Ask away.");
        return interaction;
    }

    private Interaction tellMeAboutIoC(Interaction interaction)
    {
        interaction.addToBotMessages("Inversion of Control (IoC) is the principle in software engineering which transfers the control of objects or parts of a program to a container or framework. In Spring, this is usually the ApplicationContext which respresents the IoC container. IoC enables a framework to take control of the flow of a program and make calls to our custom custom code. IoC provides decoupling of the task executions from their implementations making it easier to switch between different implementations.");
        return interaction;
    }
    private Interaction tellMeAboutSpring(Interaction interaction)
    {
        interaction.addToBotMessages("Spring is a framework that provides inversion of control via dependency injection to alleviate the complexity of developing enterprise-grade applications");
        return interaction;
    }

    private Interaction howAreYou(Interaction interaction) {
        String feeling = interaction.getSlotValue("Feeling");
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
        DecimalFormat df = new DecimalFormat("#.#");
        long id = interaction.getUserId();

        // Get a list of tests that the user has taken from the test repository
        List<TestHistory> tests = testHistoryRepository.findByUser_Id(id);

        double average = testHistoryRepository.findAverageScoreByUser(id);

        interaction.addToBotMessages("Here is your cumulative test score average: " + df.format(average * 100) + "%");
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

    static private RecognizeTextRequest getRecognizeTextRequest(String botId, String botAliasId, String localeId, String sessionId, String userInput) {
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