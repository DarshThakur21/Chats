package com.chats.Messages.Repo;

import com.chats.Messages.Model.Status;
import com.chats.Messages.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository <User, String> {
    List<User> findByStatus(Status status);


}
