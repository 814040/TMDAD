package com.mathilde.chat.websocket;


import java.text.SimpleDateFormat;
import java.util.Date; 

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;

import lombok.Data;

@Data
public class MessageWebSocket {
    private MessageType type;
    private String from;
    private String text;
    
    // getters and setters by lombok

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }

   



    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public OutputMessage send(MessageWebSocket message) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
        Date time = new Date();
        String date = formatter.format(time);        
        return new OutputMessage(message.getFrom(), message.getText(), date);
    }
    
}