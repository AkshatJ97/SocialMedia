package com.demo.social_media_project.repository;

import com.demo.social_media_project.models.Chat;
import com.demo.social_media_project.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat,Integer> {

    public List<Chat> findByUserId(Integer userId);

    @Query("Select c from Chat c where :user Member of c.user and :reqUser Member of c.user")
    public Chat findChatByUsersId(@Param("user") User user, @Param("reqUser") User reqUser);
}
