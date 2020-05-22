package com.mathilde.chat;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

/**
 * Created by rajeevkumarsingh on 25/07/17.
 */
@Component
@Slf4j
public class WebSocketEventListener {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

    //@Autowired
    //private SimpMessageSendingOperations messagingTemplate;

  

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        String username = (String) headerAccessor.getSessionAttributes().get("username");
        String password = (String) headerAccessor.getSessionAttributes().get("password");
        Long idsender = (Long) headerAccessor.getSessionAttributes().get("idsender");
        Long idreceiver = (Long) headerAccessor.getSessionAttributes().get("idreceiver");
        String date = (String) headerAccessor.getSessionAttributes().get("date");
        String textmessage = (String) headerAccessor.getSessionAttributes().get("textmessage");

        if(username != null && password != null) {
            Users user = new Users(username, password);
            // TODO
            //userRepository.save(user);
            logger.info("User created : " + username);

        }

        if(idsender != null && idreceiver != null && date != null && textmessage != null) {
    
                Message message = new Message(idsender, idreceiver, date, textmessage);
                // TODO
                //messageRepository.save(message);
                logger.info("Message created : " + textmessage);

            
        }
    }
}
