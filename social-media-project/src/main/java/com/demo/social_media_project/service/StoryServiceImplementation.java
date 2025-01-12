package com.demo.social_media_project.service;

import com.demo.social_media_project.models.Story;
import com.demo.social_media_project.models.User;
import com.demo.social_media_project.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StoryServiceImplementation implements StoryService{

    @Autowired
    UserService userService;

    @Autowired
    StoryRepository storyRepository;

    @Override
    public Story createStory(Story story, User user) {
        Story newStory = new Story();
        newStory.setCaption(story.getCaption());
        newStory.setImage(story.getImage());
        newStory.setUser(user);
        newStory.setTimestamp(LocalDateTime.now());
        return storyRepository.save(newStory);

    }

    @Override
    public List<Story> findStoryByUserId(Integer userId) {

        return storyRepository.findByUserId(userId);
    }
}
