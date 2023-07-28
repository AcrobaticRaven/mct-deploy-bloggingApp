package org.example.BloggingPlatformApi.Repository;

import org.example.BloggingPlatformApi.Model.Post;
import org.example.BloggingPlatformApi.Model.Enums.TOPIC;
import org.example.BloggingPlatformApi.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPostRepo extends JpaRepository<Post,Integer> {
    List<Post> findByPostTopic(TOPIC topic);

    User findByPostOwner(Post post);
}
