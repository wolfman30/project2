package com.learning.project2.web.lex.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Interaction {
    private String sessionId;
    private List<String> botMessages = new ArrayList<>();
    private List<String> userMessages = new ArrayList<>();

    private String intent;
    private Map<String, String> slots;
    private String state;

    public void addToSlots(String name, String value){
        slots.put(name, value);
    }

    public String getSlotValue(String name){
        return slots.get(name);
    }

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
