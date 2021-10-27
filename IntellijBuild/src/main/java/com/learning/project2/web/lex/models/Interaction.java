package com.learning.project2.web.lex.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Interaction {
    private String sessionId;
    private Long userId;
    private List<String> botMessages = new ArrayList<>();
    private List<String> userMessages = new ArrayList<>();

    private String intent;
    private Map<String, String> slots = new HashMap<>();
    private String state;

    public void addToSlots(String name, String value){
        slots.put(name, value);
    }

    @JsonIgnore
    /**
     * @param name - name of the slot
     * @returns value of the slot if one is found. Returns null if no slot with that name exists
     */
    public String getSlotValue(String name){
        return slots.get(name);
    }

    /**
     * @return returns a string array of all slot names
     */
    @JsonIgnore
    public String[] getSlotNames(){
        return slots.keySet().toArray(new String[0]);
    }

    /**
     * @param messages a list of strings to add to the bot
     */
    public void addToBotMessages(String... messages){
        botMessages.addAll(Arrays.asList(messages));
    }

    /**
     * @param messages a list of strings to add to the user
     */
    public void addToUserMessages(String... messages){
        userMessages.addAll(Arrays.asList(messages));
    }

    /**
     * @return returns the last message sent from the bot
     */
    public String getCurrentBotMessage(){
        int len = botMessages.size();
        if(len==0) return "";

        return botMessages.get(len-1);
    }

    /**
     * @return returns the last message sent from the user
     */
    public String getCurrentUserMessage(){
        int len = userMessages.size();
        if(len==0) return "";

        return userMessages.get(len-1);
    }
}
