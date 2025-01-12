package com.demo.social_media_project.controller;

import com.demo.social_media_project.models.Reels;
import com.demo.social_media_project.models.User;
import com.demo.social_media_project.service.ReelsService;
import com.demo.social_media_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReelsController {
    @Autowired
    ReelsService reelsService;

    @Autowired
    UserService userService;

    @PostMapping("api/reels")
    public Reels createReels(@RequestBody Reels reel, @RequestHeader("Authorization") String jwt){
        User reqUser = userService.findUserByJwt(jwt);
        Reels createdReels = reelsService.createReel(reel,reqUser);
        return createdReels;
    }

    @GetMapping("api/reels")
    public List<Reels> findAllReels(){
        List<Reels> reels = reelsService.findAllReels();
        return reels;
    }

    @GetMapping("api/reels/user/{userId}")
    public List<Reels> userReels(@PathVariable Integer userId) throws Exception {
        List<Reels> usersReels = reelsService.findeUsersReels(userId);
        return usersReels;
    }
}
