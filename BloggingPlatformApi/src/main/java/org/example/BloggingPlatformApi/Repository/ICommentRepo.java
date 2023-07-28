package org.example.BloggingPlatformApi.Repository;

import org.example.BloggingPlatformApi.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentRepo extends JpaRepository<Comment,Integer> {
}
