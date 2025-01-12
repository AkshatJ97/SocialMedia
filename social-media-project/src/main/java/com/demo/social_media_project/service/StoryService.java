package com.demo.social_media_project.service;

import com.demo.social_media_project.models.Story;
import com.demo.social_media_project.models.User;

import java.util.List;

public interface StoryService {
    public Story createStory(Story story, User user);
    public List<Story> findStoryByUserId(Integer userId);
}
