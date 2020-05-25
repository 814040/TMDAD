package com.mathilde.chat;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;

import lombok.Data;

@Data
public class MessageWebSocket {
    private MessageType type;
    private String from;
    private String text;
    
    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }

   



    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public OutputMessage send(MessageWebSocket message) throws Exception {
        String time = new SimpleDateFormat("HH:mm").format(new Date(0));
        return new OutputMessage(message.getFrom(), message.getText(), time);
    }
    
}