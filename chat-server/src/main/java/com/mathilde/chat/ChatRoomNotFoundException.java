package com.mathilde.chat;

class ChatRoomNotFoundException extends RuntimeException {

  ChatRoomNotFoundException(Long id) {
    super("Could not find chat " + id);
  }
}