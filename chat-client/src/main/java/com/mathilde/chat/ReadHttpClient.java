package com.mathilde.chat;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ReadHttpClient {
  static ResponseEntity<String> message;
  static ResponseEntity<String> user;
  final static String MESSAGE_URI = "http://localhost:8080/message";
  final static String USER_URI = "http://localhost:8080/user";

  // collect information from message page
  public static String getMessage() {
    RestTemplate restTemplate = new RestTemplate();
    message = restTemplate.getForEntity(MESSAGE_URI, String.class);
    return message.getBody();
  }

  // collect information from user page
  public static String getUser() {
    RestTemplate restTemplate = new RestTemplate();
    user = restTemplate.getForEntity(USER_URI, String.class);
    return user.getBody();
  }

  // general function to collect string infomation
  public static List<String> getElement(String textJSON, String element){
    List<String> reponse = new ArrayList<>();
    int indexStart = 0;
    int indexEnd = 0;
    for (int i = 0; i <textJSON.length(); i++){
      char charI = textJSON.charAt(i);
      if (charI=='{') {
        indexStart = i;
      }
      if (charI=='}') {
        indexEnd = i;
        JSONObject arr = new JSONObject(sliceRange(textJSON,indexStart, indexEnd+1));
        String value = (String) arr.get(element);
        reponse.add(value);
      } 
    }
    return reponse;
  }

  public static List<String> getUserList(){
    String textJSON = getUser();
    List<String> reponse = new ArrayList<>();
    int indexStart = 0;
    int indexEnd = 0;
    for (int i = 0; i <textJSON.length(); i++){
      char charI = textJSON.charAt(i);
      if (charI=='{') {
        indexStart = i;
      }
      if (charI=='}') {
        indexEnd = i;
        JSONObject arr = new JSONObject(sliceRange(textJSON,indexStart, indexEnd+1));
        String user = (String) arr.get("username");
        reponse.add(user);
      } 
    }
    return reponse;
  }

  // connect user
  public static int connectUser(String username, String password){
    String textJSON = getUser();
    int reponse = -1;
    int indexStart = 0;
    int indexEnd = 0;
    for (int i = 0; i <textJSON.length(); i++){
      char charI = textJSON.charAt(i);
      if (charI=='{') {
        indexStart = i;
      }
      if (charI=='}') {
        indexEnd = i;
        JSONObject arr = new JSONObject(sliceRange(textJSON,indexStart, indexEnd+1));
        String usernameJSON = (String) arr.get("username");
        if (usernameJSON.equals(username)){
          String passwordJSON = (String) arr.get("password");
          if (passwordJSON.equals(password)){
            //get id here
            reponse = (int) arr.get("iduser"); //Client Connected
            break;
          } else {reponse = 0; break;} //Incorrect Password
        } else {reponse = -1;}//Client Does Not Exist
      } 
    }
    return reponse;
  }



  // return list of messages sent or received by user (iduser)
  /*public static List<String> getMessageList(int iduser){
    String textJSON = ReadHttpClient.getMessage();
    List<String> reponse = new ArrayList<>();
    int indexStart = 0;
    int indexEnd = 0;
    for (int i = 0; i <textJSON.length(); i++){
      char charI = textJSON.charAt(i);
      if (charI=='{') {
        indexStart = i;
      }
      if (charI=='}') {
        indexEnd = i;
        JSONObject arr = new JSONObject(sliceRange(textJSON,indexStart, indexEnd+1));
        int idsender = (int) arr.get("idSender");
        int idreceiver = (int) arr.get("idReceiver");
        
        String textmessage = (String) arr.get("textmessage");
        String date = (String) arr.get("date");
        if (idsender == iduser){
          String username = getUserById(idreceiver);
          reponse.add("Send : "+textmessage +"\nTo : "+ username+"\nAt "+ date+"\n-------------");
        }
        else if (idreceiver == iduser){
          String username = getUserById(idsender);
          reponse.add("Received : "+textmessage +"\nFrom : "+ username+"\nAt "+ date+"\n-------------");
        }
      } 
    }
    return reponse;
  }*/

  // return list of people who chat with user (iduser)
  public static List<String> getChatList(int iduser){
    List<String> reponse = new ArrayList<>();
    String textJSON = ReadHttpClient.getMessage();
    int indexStart = 0;
    int indexEnd = 0;    
    for (int i = 0; i <textJSON.length(); i++){
      char charI = textJSON.charAt(i);
      if (charI=='{') {
        indexStart = i;
      }
      if (charI=='}') {
        indexEnd = i;
        JSONObject arr = new JSONObject(sliceRange(textJSON,indexStart, indexEnd+1));
        int idsender = (int) arr.get("idSender");
        int idreceiver = (int) arr.get("idReceiver");
        if (idsender == iduser){
          String chat = getUserById(idreceiver);
          if (!reponse.contains(chat)) reponse.add(chat);
        }
        else if (idreceiver == iduser){
          String chat = getUserById(idsender);
          if (!reponse.contains(chat)) reponse.add(chat);
        }
      } 
    }
    return reponse;
  }

  // return list of messages from a chat
  public static List<String> getMessageFromChat(int iduser, String chatUsername){
    List<String> reponse = new ArrayList<>();
    int idchatuser = getIdByUser(chatUsername);
    if (idchatuser==-1){reponse.add("USER DOES NOT EXIST");}
    else{
    String textJSON = ReadHttpClient.getMessage();
    
    int indexStart = 0;
    int indexEnd = 0;    
    for (int i = 0; i <textJSON.length(); i++){
      char charI = textJSON.charAt(i);
      if (charI=='{') {
        indexStart = i;
      }
      if (charI=='}') {
        indexEnd = i;
        JSONObject arr = new JSONObject(sliceRange(textJSON,indexStart, indexEnd+1));
        int idsender = (int) arr.get("idSender");
        int idreceiver = (int) arr.get("idReceiver");
        
        String textmessage = (String) arr.get("textmessage");
        String date = (String) arr.get("date");
        if (idsender == iduser && idreceiver == idchatuser){
          String username = getUserById(idreceiver);
          reponse.add("SEND : "+textmessage +"\nTO : "+ username+"\nAT "+ date+"\n-------------");
        }
        else if (idreceiver == iduser && idsender == idchatuser){
          String username = getUserById(idsender);
          reponse.add("RECEIVED : "+textmessage +"\nFROM : "+ username+"\nAT "+ date+"\n-------------");
        }
      } 
    }
  }
    return reponse;
  }




  private static String getUserById(int iduser) {
    String textJSON = getUser();
    String reponse="";
    int indexStart = 0;
    int indexEnd = 0;
    for (int i = 0; i <textJSON.length(); i++){
      char charI = textJSON.charAt(i);
      if (charI=='{') {
        indexStart = i;
      }
      if (charI=='}') {
        indexEnd = i;
        JSONObject arr = new JSONObject(sliceRange(textJSON,indexStart, indexEnd+1));
        int iduserJSON = (int) arr.get("iduser");
        if (iduserJSON==iduser){
          reponse = (String) arr.get("username");
          break;
        } else {reponse = "THIS USER DOES NOT EXIST";}
      } 
    }
    return reponse;
  }

  public static int getIdByUser(String username) {
    String textJSON = getUser();
    int reponse=-1;
    int indexStart = 0;
    int indexEnd = 0;
    for (int i = 0; i <textJSON.length(); i++){
      char charI = textJSON.charAt(i);
      if (charI=='{') {
        indexStart = i;
      }
      if (charI=='}') {
        indexEnd = i;
        JSONObject arr = new JSONObject(sliceRange(textJSON,indexStart, indexEnd+1));
        String usernameJSON = (String) arr.get("username");
        if (usernameJSON.equals(username)){
          reponse = (int) arr.get("iduser");
          break;
        }
      } 
    }
    return reponse;
  }

  private static String sliceRange(String s, int startIndex, int endIndex) {
    if (startIndex < 0) startIndex = s.length() + startIndex;
    if (endIndex < 0) endIndex = s.length() + endIndex;
    return s.substring(startIndex, endIndex);
  }

	public static String listStringToString(List<String> list) {
		String delim = "\n";
		StringBuilder sb = new StringBuilder();
		int i = 0;
		while (i < list.size() - 1) {
			sb.append(list.get(i));
			sb.append(delim);
			i++;
		}
		sb.append(list.get(i));
		String res = sb.toString();
		return res;
	}
}