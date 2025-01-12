package com.demo.social_media_project.controller;

import com.demo.social_media_project.models.Story;
import com.demo.social_media_project.models.User;
import com.demo.social_media_project.service.StoryService;
import com.demo.social_media_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StoryController {
    @Autowired
    private StoryService storyService;
    @Autowired
    private UserService userService;
    @PostMapping("/api/story")
    public Story createStory(@RequestBody Story story, @RequestHeader("Authorization") String jwt){
        User reqUser = userService.findUserByJwt(jwt);
        return storyService.createStory(story,reqUser);
    }
    @GetMapping("/api/story/user/{userId}")
    public List<Story> findStoryByUserId(@PathVariable Integer userId){
        return storyService.findStoryByUserId(userId);
    }
}
