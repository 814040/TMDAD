package com.mathilde.chat;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;




@Data
@Entity
class Message {

  private @Id @GeneratedValue Long idmessage;
  //@OneToOne(cascade = {CascadeType.ALL})
  //@JoinColumn(name = "idsender", referencedColumnName = "iduser")
  private Long idSender;
  //@ManyToOne(fetch = FetchType.LAZY)
  //@JoinColumn(name = "idreceiver", referencedColumnName = "iduser")
  private Long idReceiver;
  //private String state;  
  private String date;
  private String textmessage;

  Message() {}

  Message(Long idSender, Long idReceiver, String date, String textmessage) {
    this.idSender = idSender;
    this.idReceiver = idReceiver;
    //this.state = "SEND";
    this.date = date;
    this.textmessage = textmessage;
  }
}

