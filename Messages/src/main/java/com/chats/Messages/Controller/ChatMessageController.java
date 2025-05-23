package com.chats.Messages.Controller;

import com.chats.Messages.Model.ChatMessage;
import com.chats.Messages.Model.ChatNotification;
import com.chats.Messages.Service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatMessageController{

    private final SimpMessagingTemplate simpleMessagingTemplate;
    private final ChatMessageService chatMessageService;


    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage){
        ChatMessage savedMsg=chatMessageService.save(chatMessage);
        simpleMessagingTemplate.convertAndSendToUser(chatMessage.getRecipientId(),"/queue/messages",
                ChatNotification.builder()
                        .id(savedMsg.getId())
                        .senderId(savedMsg.getSenderId())
                        .recipientId(savedMsg.getRecipientId())
                        .content(savedMsg.getContent())
                        .build()
        );


    }




    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<List<ChatMessage>>   findChatMessages(
            @PathVariable String senderId,@PathVariable String recipientId){
        return  ResponseEntity.ok(chatMessageService.findChatMessage(senderId,recipientId));
    }






}
