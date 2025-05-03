package com.chats.Messages.Model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
//@AllArgsConstructor
//@NoArgsConstructor
@Document
public class User {

    @Id
    private String nickName;
    private String fullName;
    private Status   status;


}
