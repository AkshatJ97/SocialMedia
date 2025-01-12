package com.demo.social_media_project.controller;

import com.demo.social_media_project.models.Comment;
import com.demo.social_media_project.models.User;
import com.demo.social_media_project.service.CommentService;
import com.demo.social_media_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    UserService userService;
    @PostMapping("/api/comments/post/{postId}")
    public Comment createComment(@RequestBody Comment comment,
                                 @RequestHeader("Authorization") String jwt,
                                 @PathVariable Integer postId) throws Exception {
        User user = userService.findUserByJwt(jwt);
        Comment createdComment = commentService.createComments(comment, postId,user.getId());
        return createdComment;
    }

    @PutMapping("/api/comments/like/{commentId}")
    public Comment likeComment(@RequestHeader("Authorization") String jwt,
                                 @PathVariable Integer commentId) throws Exception {
        User user = userService.findUserByJwt(jwt);
        Comment likedComment = commentService.likedComment(commentId,user.getId());
        return likedComment;
    }

}
