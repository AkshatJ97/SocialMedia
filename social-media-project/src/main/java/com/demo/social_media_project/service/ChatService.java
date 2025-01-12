package com.demo.social_media_project.service;

import com.demo.social_media_project.models.Chat;
import com.demo.social_media_project.models.User;

import java.util.List;

public interface ChatService {
    public Chat createChat(User reqUser, User user);
    public Chat findChatById(Integer chatId) throws Exception;
    public List<Chat> findUsersChat(Integer userId);
}
