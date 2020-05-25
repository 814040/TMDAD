package com.mathilde.chat;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;




@Data
@Entity
class ChatRoom {

  private @Id @GeneratedValue Long idChatRoom;
  private String nameChatRoom;

  ChatRoom() {}

  ChatRoom(Long idChatRoom, String nameChatRoom) {
    this.idChatRoom = idChatRoom;
    this.nameChatRoom = nameChatRoom;
    
  }
}

