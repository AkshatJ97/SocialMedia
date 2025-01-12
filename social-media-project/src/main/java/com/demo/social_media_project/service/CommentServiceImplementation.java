package com.demo.social_media_project.service;

import com.demo.social_media_project.models.Comment;
import com.demo.social_media_project.models.Post;
import com.demo.social_media_project.models.User;
import com.demo.social_media_project.repository.CommentRepository;
import com.demo.social_media_project.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentServiceImplementation implements CommentService{
    @Autowired
    UserService userService;

    @Autowired
    PostService postService;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    PostRepository postRepository;

    @Override
    public Comment createComments(Comment comments, Integer postId, Integer userId) throws Exception {
        User user = userService.findUserById(userId);
        Post post = postService.findPostById(postId);
        comments.setContent(comments.getContent());
        comments.setCreatedAt(LocalDateTime.now());
        Comment savedComment = commentRepository.save(comments);

        post.getComments().add(savedComment);
        postRepository.save(post);

        return savedComment;
    }

    @Override
    public Comment findCommentById(Integer commentId) throws Exception {
        Optional<Comment> opt = commentRepository.findById(commentId);
        if(opt.isEmpty()){
            throw new Exception("Comment does not exist");
        }
        return opt.get();
    }

    @Override
    public Comment likedComment(Integer commentId, Integer userId) throws Exception {
        Comment comment = findCommentById(commentId);
        User user = userService.findUserById(userId);

        if(!comment.getLiked().contains(user)){
            comment.getLiked().add(user);
        }else comment.getLiked().remove(user);
        return commentRepository.save(comment);
    }
}
