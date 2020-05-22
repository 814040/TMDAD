package com.mathilde.chat;

class UserNotFoundException extends RuntimeException {

  UserNotFoundException(Long id) {
    super("Could not find user " + id);
  }
}