package com.demo.social_media_project.service;

import com.demo.social_media_project.models.Reels;
import com.demo.social_media_project.models.User;

import java.util.List;

public interface ReelsService {

    public Reels createReel(Reels reel, User user);

    public List<Reels> findAllReels();

    public List<Reels> findeUsersReels(Integer userId) throws Exception;
}
