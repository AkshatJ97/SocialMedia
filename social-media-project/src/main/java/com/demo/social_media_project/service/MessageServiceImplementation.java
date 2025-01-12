package com.demo.social_media_project.service;

import com.demo.social_media_project.models.Chat;
import com.demo.social_media_project.models.Message;
import com.demo.social_media_project.models.User;
import com.demo.social_media_project.repository.ChatRepository;
import com.demo.social_media_project.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImplementation implements MessageService{
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    ChatService chatService;

    @Override
    public Message createMessage(User user, Integer chatId, Message message) throws Exception {
        Message newMessage = new Message();
        Chat chat = chatService.findChatById(chatId);
        newMessage.setContent(message.getContent());
        newMessage.setUser(user);
        newMessage.setChat(chat);
        newMessage.setImage(message.getImage());
        newMessage.setTimestamp(LocalDateTime.now());


        return messageRepository.save(newMessage);
    }

    @Override
    public List<Message> findChatsMessages(Integer chatId) throws Exception {
        Chat chat = chatService.findChatById(chatId);
        return messageRepository.findByChat_ChatId(chatId);
    }
}
