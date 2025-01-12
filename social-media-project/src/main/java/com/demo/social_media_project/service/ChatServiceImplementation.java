package com.demo.social_media_project.service;

import com.demo.social_media_project.models.Chat;
import com.demo.social_media_project.models.User;
import com.demo.social_media_project.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImplementation implements ChatService{

    @Autowired
    ChatRepository chatRepository;

    @Override
    public Chat createChat(User reqUser, User user) {
        Chat isExist = chatRepository.findChatByUsersId(reqUser, user);
        if(isExist!=null){
            return isExist;
        }
        Chat chat = new Chat();
        chat.getUser().add(user);
        chat.getUser().add(reqUser);
        chat.setTimestamp(LocalDateTime.now());
        return chatRepository.save(chat);
    }

    @Override
    public Chat findChatById(Integer chatId) throws Exception {
        Optional<Chat> chat = chatRepository.findById(chatId);
        if(chat.isEmpty()){
            throw new Exception("Chat Not Found with id: "+chatId);
        }
        return chat.get();
    }

    @Override
    public List<Chat> findUsersChat(Integer userId) {
        return chatRepository.findByUserId(userId);
    }
}
