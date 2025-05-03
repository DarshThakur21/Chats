package com.chats.Messages.Service;


import com.chats.Messages.Model.Status;
import com.chats.Messages.Model.User;
import com.chats.Messages.Repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public void saveUser(User user){
         user.setStatus(Status.ONLINE);

         userRepository.save(user);


    }

    public void disconnect(User user){
        var storedUser=userRepository.findById(user.getNickName()).orElse(null);

        if (storedUser!=null){
            user.setStatus(Status.OFFLINE);
            userRepository.save(user);
        }

    }
    public List<User> findConnectedUser(){
     return  userRepository.findByStatus(Status.ONLINE);
    }

}
