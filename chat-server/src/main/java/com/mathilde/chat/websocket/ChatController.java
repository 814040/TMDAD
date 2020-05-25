package com.mathilde.chat.websocket;

//import com.mathilde.chat.*;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public MessageWebSocket sendMessage(@Payload MessageWebSocket messageWS) {
        return messageWS;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public MessageWebSocket addUser(@Payload MessageWebSocket messageWS,
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", messageWS.getFrom());
        return messageWS;
    }

}
