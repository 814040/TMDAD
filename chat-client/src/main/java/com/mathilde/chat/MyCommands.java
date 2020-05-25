package com.mathilde.chat;

import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellComponent;
import java.text.SimpleDateFormat;  
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@ShellComponent
public class MyCommands {

    int ID_CLIENT = -1;
    List <String> emptyList = new ArrayList<>();

    @ShellMethod("Get String Element From Page (user or message)")
    public String getElement(String page, String element) {
        String text;
        if (page.equals("message")){text = ReadHttpClient.getMessage();}
        else if (page.equals("user")){text = ReadHttpClient.getUser();}
        else {return "PAGE DOES NOT EXIST";}
        return ReadHttpClient.listStringToString(ReadHttpClient.getElement(text, element));
    }

    @ShellMethod("Get User List")
    public String userList() {
        return ReadHttpClient.listStringToString(ReadHttpClient.getUserList());
    }

    @ShellMethod("Connect User")
    public String connect(String username, String password) {
        WebSocketEventSender.connectWebSocket();
        String message= "";
        int connection = ReadHttpClient.connectUser(username,password);
        if (connection >0){
            message = "CLIENT CONNECTED";
            ID_CLIENT = connection;
        }
        else if (connection == 0) message = "INCORRECT PASSWORD";
        else if (connection == -1) message = "USER DOES NOT EXIST";
        
        return message;
    }  

    @ShellMethod("Disconnect User")
    public String disconnect() {
        ID_CLIENT = -1;
        return "CLIENT DISCONNECTED";
    } 

    @ShellMethod("Add User -- Choose your password")
    public String addUser(String username, String password) {
        String reponse;
        boolean result = false; // WebSocketEventSender.addUser(username,password);
        if (result) reponse = "NEW USER " + username+ "CREATED";
        else reponse = "USER CREATION FAILED";
        return reponse;
    } 


    @ShellMethod("Write Message")
    public String writeMessage(String receivername, String textmessage) {
        String reponse;
        int idreceiver = ReadHttpClient.getIdByUser(receivername);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
        Date time = new Date();   
        String date = formatter.format(time);
        boolean result = false; //WebSocketEventSender.addMessage(ID_CLIENT, idreceiver, date,textmessage);
        if (result) reponse = "NEW MESSAGE CREATED";
        else reponse = "MESSAGE CREATION FAILED";
        return reponse;
    } 


    @ShellMethod("Get Chat List")
    public String chatList() {
        String reponse;
        if (ID_CLIENT == -1){
            reponse = "YOU MUST CONNECT FIRST";
        } 
        else {
            List<String> reponselist = ReadHttpClient.getChatList(ID_CLIENT);
            if (reponselist.equals(emptyList)) reponse = "NO CHAT WITH THIS USER";
            else reponse = ReadHttpClient.listStringToString(reponselist);
        }
        return reponse;
    }

    @ShellMethod("Get Message List from a Chat")
    public String messageByChat(int chatroom) {
        String reponse;
        if (ID_CLIENT == -1){
            reponse = "YOU MUST CONNECT FIRST";
        } 
        else {
            List<String> reponselist = ReadHttpClient.getMessageFromChat(chatroom);
            if (reponselist.equals(emptyList)) reponse = "NO MESSAGE IN THIS CHAT";
            else reponse = ReadHttpClient.listStringToString(reponselist);
        }
        return reponse;
    }

}
