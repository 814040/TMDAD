package com.mathilde.chat;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Data
@Entity
class ChatRoom {

  private @Id @GeneratedValue Long idChatRoom;
  private Long user1ChatRoom;
  private Long user2ChatRoom;

  ChatRoom() {}

  ChatRoom(Long user1ChatRoom, Long user2ChatRoom) {
    this.user1ChatRoom = user1ChatRoom;
    this.user2ChatRoom = user2ChatRoom;    
  }
}

