package com.demo.social_media_project.service;

import com.demo.social_media_project.models.Chat;
import com.demo.social_media_project.models.Message;
import com.demo.social_media_project.models.User;

import java.util.List;

public interface MessageService {

    public Message createMessage(User user, Integer chatId, Message message) throws Exception;

    public List<Message> findChatsMessages(Integer chatId) throws Exception;

}
