package com.chats.Messages.Repo;

import com.chats.Messages.Model.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatMessageRepo  extends MongoRepository<ChatMessage,String> {


    List<ChatMessage> findByChatId(String s);
}
