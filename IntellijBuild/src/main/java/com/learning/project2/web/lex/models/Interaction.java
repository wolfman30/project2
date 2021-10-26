package com.learning.project2.web.lex.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
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
    public String getSlotValue(String name){
        return slots.get(name);
    }

    @JsonIgnore
    public String[] getSlotNames(){
        return slots.keySet().toArray(new String[0]);
    }

    public void addToBotMessages(String... strs){
        botMessages.addAll(Arrays.asList(strs));
    }

    public void addToUserMessages(String... strs){
        userMessages.addAll(Arrays.asList(strs));
    }

    public String getCurrentBotMessage(){
        int len = botMessages.size();
        if(len==0) return "";

        return botMessages.get(len-1);
    }

    public String getCurrentUserMessage(){
        int len = userMessages.size();
        if(len==0) return "";

        return userMessages.get(len-1);
    }
}
