package com.example.chatserverspring.repo;

import com.example.chatserverspring.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

     //@Query("SELECT m FROM Message m WHERE m.id > :id AND m.to = :owner")
     @Query("SELECT m FROM Message m WHERE m.id > :id")
     List<Message> findNew(long id);

     @Query("SELECT m FROM Message m WHERE m.id = :id")
     Message findFile(long id);

     @Query("SELECT m FROM Message m WHERE m.to = :to")
     List<Message> findPrivate(String to);

     @Query("SELECT from FROM Message")
     List<String> getUsers();


}
