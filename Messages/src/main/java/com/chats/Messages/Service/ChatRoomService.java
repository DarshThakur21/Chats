package com.chats.Messages.Service;

import com.chats.Messages.Model.ChatRoom;
import com.chats.Messages.Repo.ChatRoomRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepo chatRoomRepo;


    public Optional<String> getChatRoomId(String senderId,String recipientId, boolean newRoom){
        return  chatRoomRepo.findBySenderIdAndRecipientId(senderId,recipientId)
                .map(ChatRoom::getChatId)
                .or(
                ()->{
                    if (newRoom){
                        var chatId=createChatId(senderId,recipientId);
                        return  Optional.of(chatId);
                    }
                    return  Optional.empty();
                }
        );
    }

    private String createChatId(String senderId, String recipientId) {
        var chatId=String.format("%s_%s",senderId,recipientId);

            ChatRoom senderRecipient=ChatRoom.builder()
                    .chatId(chatId)
                    .senderId(senderId)
                    .recipientId(recipientId)
                    .build();


        ChatRoom recipientSender=ChatRoom.builder()
                .chatId(chatId)
                .senderId(recipientId)
                .recipientId(senderId)
                .build();
        chatRoomRepo.save(senderRecipient);
        chatRoomRepo.save(recipientSender);
        return chatId;
    }

}
