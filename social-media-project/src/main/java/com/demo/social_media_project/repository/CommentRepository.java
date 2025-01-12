package com.demo.social_media_project.repository;

import com.demo.social_media_project.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
}
