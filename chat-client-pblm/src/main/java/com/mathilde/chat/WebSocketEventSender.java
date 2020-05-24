package com.mathilde.chat;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

@Controller
public class WebSocketEventSender {

    public static void connectWebSocket(){
        WebSocketClient client = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(client);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        
        StompSessionHandler sessionHandler = new MyStompSessionHandler();
        stompClient.connect(URL, sessionHandler);
        
        new Scanner(System.in).nextLine(); // Don't close immediately.
    }



    @Override
    public void afterConnected(
      StompSession session, StompHeaders connectedHeaders) {
        session.subscribe("/topic/messages", this);
        session.send("/app/chat", getSampleMessage());
    }
    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        Message msg = (Message) payload;
        logger.info("Received : " + msg.getText()+ " from : " + msg.getFrom());
    }
















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