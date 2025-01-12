package com.demo.social_media_project.controller;

import com.demo.social_media_project.models.Post;
import com.demo.social_media_project.models.User;
import com.demo.social_media_project.response.ApiResponse;
import com.demo.social_media_project.service.PostService;
import com.demo.social_media_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    @Autowired
    PostService postService;
    @Autowired
    UserService userService;
    @PostMapping("api/posts")
    public ResponseEntity<Post> createPost(@RequestBody Post post, @RequestHeader("Authorization") String jwt) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        Post newPost = postService.createNewPost(post,reqUser.getId());
        return new ResponseEntity<>(newPost, HttpStatus.OK);
    }
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId, @RequestHeader("Authorization") String jwt) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        String message = postService.deletePost(postId,reqUser.getId());
        ApiResponse res = new ApiResponse(message,true);
        return new ResponseEntity<ApiResponse>(res,HttpStatus.OK);
    }
    @GetMapping("/posts/{postId}")
    public ResponseEntity<Post> findPostById(@PathVariable Integer postId) throws Exception {
        Post post = postService.findPostById(postId);
        return new ResponseEntity<>(post,HttpStatus.OK);
    }
    @GetMapping("/posts/users/{userId}")
    public ResponseEntity<List<Post>> findUsersPost(@PathVariable Integer userId){
        List<Post> post = postService.findPostByUserId(userId);
        return new ResponseEntity<List<Post>>(post,HttpStatus.OK);
    }

    @GetMapping("/api/posts")
    public ResponseEntity<List<Post>> findAllPost(){
        List<Post> post = postService.findAllPost();
        return new ResponseEntity<List<Post>>(post,HttpStatus.OK);
    }

    @PutMapping("/posts/save/{postId}")
    public ResponseEntity<Post> savedPostHandler(@PathVariable Integer postId, @RequestHeader("Authorization") String jwt) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        Post post = postService.savedPost(postId,reqUser.getId());
        return new ResponseEntity<>(post,HttpStatus.OK);
    }

    @PutMapping("/api/posts/like/{postId}")
    public ResponseEntity<Post> likePost(@PathVariable Integer postId, @RequestHeader("Authorization") String jwt) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        Post post = postService.likePost(postId, reqUser.getId());
        return new ResponseEntity<>(post,HttpStatus.OK);
    }


}
