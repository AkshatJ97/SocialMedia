package com.demo.social_media_project.controller;

import com.demo.social_media_project.models.Chat;
import com.demo.social_media_project.models.User;
import com.demo.social_media_project.request.CreateChatRequest;
import com.demo.social_media_project.service.ChatService;
import com.demo.social_media_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChatController {
    @Autowired
    ChatService chatService;
    @Autowired
    UserService userService;
    @PostMapping("/api/chats")
    public Chat createChat(@RequestHeader("Authorization") String jwt, @RequestBody CreateChatRequest req) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        User user2 = userService.findUserById(req.getUserId());

        return chatService.createChat(reqUser,user2);
    }
    @GetMapping("/api/chats")
    public List<Chat> findUsersChat(@RequestHeader("Authorization") String jwt){
        User reqUser = userService.findUserByJwt(jwt);
        return chatService.findUsersChat(reqUser.getId());
    }
}
