package com.demo.social_media_project.service;

import com.demo.social_media_project.models.Comment;

public interface CommentService {

    public Comment createComments(Comment comments, Integer postId, Integer userId) throws Exception;

    public Comment findCommentById(Integer commentId) throws Exception;
    public Comment likedComment(Integer commentId,Integer userId) throws Exception;
}
