package com.chats.Messages.Model;

import lombok.*;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ChatNotification {

    @Id
    private String id;
    private String senderId;
    private String recipientId;
    private String content;
}
