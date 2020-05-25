package com.mathilde.chat;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;




@Data
@Entity
class Message {

  private @Id @GeneratedValue Long idmessage;
  private Long idSender;
  private Long idChatRoom; 
  private String date;
  private String textmessage;

  Message() {}

  Message(Long idSender, Long idChatRoom, String date, String textmessage) {
    this.idSender = idSender;
    this.idChatRoom = idChatRoom;
    //this.state = "SEND";
    this.date = date;
    this.textmessage = textmessage;
  }
}

