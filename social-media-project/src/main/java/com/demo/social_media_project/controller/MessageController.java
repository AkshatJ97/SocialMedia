package com.demo.social_media_project.controller;

import com.demo.social_media_project.models.Message;
import com.demo.social_media_project.models.User;
import com.demo.social_media_project.service.MessageService;
import com.demo.social_media_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {
    @Autowired
    MessageService messageService;
    @Autowired
    UserService userService;
    @PostMapping("/api/message/chat/{chatId}")
    public Message createMessage(@RequestHeader("Authorization") String jwt, @PathVariable Integer chatId, @RequestBody Message message) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        return messageService.createMessage(reqUser,chatId,message);
    }

    @GetMapping("/api/message/chat/{chatId}")
    public List<Message> findChatMessage(@PathVariable Integer chatId) throws Exception {
        return messageService.findChatsMessages(chatId);
    }
}
