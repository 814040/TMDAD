package com.mathilde.chat;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketEventSender {

    SimpMessageHeaderAccessor headerAccessor;
    @MessageMapping("/user")
    @SendTo("/user/info")
    public static boolean addUser(String username, String password){
        // TODO
        //headerAccessor.getSessionAttributes().put("username", username);
        //headerAccessor.getSessionAttributes().put("password", password);
        return false;
    }


    @MessageMapping("/message")
    @SendTo("/message/info")
    public static boolean addMessage(int idsender, int idreceiver, String date, String textmessage) {
        // TODO
        //headerAccessor.getSessionAttributes().put("idsender", idsender);
        //headerAccessor.getSessionAttributes().put("idreceiver", idreceiver);
        //headerAccessor.getSessionAttributes().put("date", date);
        //headerAccessor.getSessionAttributes().put("textmessage", textmessage);
        return false;
    }
}