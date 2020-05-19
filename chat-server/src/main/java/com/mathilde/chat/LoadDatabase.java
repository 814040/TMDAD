package com.mathilde.chat;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.text.SimpleDateFormat;  
import java.util.Date;  

@Configuration
@Slf4j
class LoadDatabase {

  Users Mathilde = new Users("Mathilde", "xxxx");
  Users José = new Users("José", "xxxx");
  SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
  Date time = new Date();   
  String date = formatter.format(time);

  
  @Bean
  CommandLineRunner initDatabase(UserRepository userRepository, MessageRepository messageRepository) {
    //System.out.println(Mathilde.getIduser().toString());
    return args -> {
      log.info("Preloading " + userRepository.save(Mathilde));
      log.info("Preloading " + userRepository.save(José));
      log.info("Preloading " + messageRepository.save(new Message(Mathilde.getIduser(), José.getIduser(), date,"Hola!")));
      log.info("Preloading " + messageRepository.save(new Message(José.getIduser(), Mathilde.getIduser(), date,"Buenas!")));
        
      };
  }
}