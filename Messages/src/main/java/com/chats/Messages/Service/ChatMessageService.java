package com.chats.Messages.Service;

import com.chats.Messages.Model.ChatMessage;
import com.chats.Messages.Repo.ChatMessageRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatMessageRepo chatMessageRepo;
    private final ChatRoomService chatRoomService;

    public ChatMessage save(ChatMessage chatMessage){
        var chatId=chatRoomService.getChatRoomId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true)
                .orElseThrow(() -> new RuntimeException("am having issue at the chatmessage service"));

        chatMessage.setChatId(chatId);
        chatMessageRepo.save(chatMessage);
        return chatMessage;
    }

    public List<ChatMessage> findChatMessage(String senderId, String recipientId){
        var chatId=chatRoomService.getChatRoomId(senderId,recipientId,false);

        return  chatId.map(chatMessageRepo::findByChatId).orElse(new ArrayList<>());
    }



}
