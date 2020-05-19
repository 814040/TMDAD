package com.mathilde.chat;


import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellComponent;

@ShellComponent
public class MyCommands {

    int ID_CLIENT = -1;
    @ShellMethod("Add two integers together.")
    public int add(final int a, final int b) {
        return a + b;
    }
    

    @ShellMethod("Get String Element From Page (user or message)")
    public String getElement(String page, String element) {
        String text;
        if (page.equals("message")){text = ReadHttpClient.getMessage();}
        else if (page.equals("user")){text = ReadHttpClient.getUser();}
        else {return "PAGE DOES NOT EXIST";}
        return ReadHttpClient.listStringToString(ReadHttpClient.getElement(text, element));
    }

    @ShellMethod("Connect User")
    public String connect(String username, String password) {
        String message= "";
        int connection = ReadHttpClient.connectUser(username,password);
        if (connection >0){
            message = "Client Connected";
            ID_CLIENT = connection;
        }
        else if (connection == 0) message = "Incorrect Password";
        else if (connection == -1) message = "User Does Not Exist";
        return message;
    }  

    @ShellMethod("Disconnect User")
    public String disconnect() {
        ID_CLIENT = -1;
        return "Client Disconnected";
    } 

    @ShellMethod("Get List Message")
    public String messageList() {
        String reponse;
        if (ID_CLIENT == -1){
            reponse = "You must connect first";
        } else reponse = ReadHttpClient.listStringToString(ReadHttpClient.getMessage(ID_CLIENT));
        return reponse;
    }

    @ShellMethod("Get Messages from a Chat")
    public String messageChat(String chatUsername) {
        String reponse;
        if (ID_CLIENT == -1){
            reponse = "You must connect first";
        } else reponse = ReadHttpClient.listStringToString(ReadHttpClient.getChatMessage(ID_CLIENT, chatUsername));
        return reponse;
    }


}
