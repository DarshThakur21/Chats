package com.chats.Messages.Repo;

import com.chats.Messages.Model.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ChatRoomRepo  extends MongoRepository <ChatRoom,String> {
    Optional<ChatRoom> findBySenderIdAndRecipientId(String senderId,String recipientId);
}
