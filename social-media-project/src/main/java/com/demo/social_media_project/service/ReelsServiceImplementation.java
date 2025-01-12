package com.demo.social_media_project.service;

import com.demo.social_media_project.models.Reels;
import com.demo.social_media_project.models.User;
import com.demo.social_media_project.repository.ReelsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReelsServiceImplementation implements ReelsService{
    @Autowired
    private ReelsRepository reelsRepository;

    @Autowired
    private UserService userService;

    @Override
    public Reels createReel(Reels reel, User user) {
        Reels createReel = new Reels();

        createReel.setTitle(reel.getTitle());
        createReel.setUser(user);
        createReel.setVideo(reel.getVideo());

        return reelsRepository.save(createReel);
    }

    @Override
    public List<Reels> findAllReels() {
        return reelsRepository.findAll();
    }

    @Override
    public List<Reels> findeUsersReels(Integer userId) throws Exception {
        User user = userService.findUserById(userId);
        return reelsRepository.findByUserId(userId);
    }
}
