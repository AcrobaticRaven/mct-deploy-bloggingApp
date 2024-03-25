package org.example.BloggingPlatformApi.ServiceTest;

import org.example.BloggingPlatformApi.Model.Comment;
import org.example.BloggingPlatformApi.Model.Enums.GENDER;
import org.example.BloggingPlatformApi.Model.Enums.TOPIC;
import org.example.BloggingPlatformApi.Model.Post;
import org.example.BloggingPlatformApi.Model.User;
import org.example.BloggingPlatformApi.Repository.ICommentRepo;
import org.example.BloggingPlatformApi.Service.CommentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CommentServiceTest {

    @Mock
    ICommentRepo commentRepo;

    @InjectMocks
    CommentService commentService;

    @Test
    public void test_addComment(){
        User user = new User();
        Post post = new Post();
        user.setUserName("balaji");
        user.setUserPassword("123");
        user.setUserMail("abc@gmail.com");
        user.setUserNumber(1);
        user.setGender(GENDER.MALE);
        post.setPostTopic(TOPIC.FOOD);
        post.setPostOwner(user);
        Comment comment= new Comment();
        comment.setCommentId(1);
        comment.setCommentText("hey");
        comment.setCommentedPost(post);
        comment.setCommentedUser(user);
    //    commentRepo.save(comment);

        when(commentRepo.save(comment)).thenReturn(comment);
        assertEquals("Comment has been added successfully",commentService.addComment(comment));

    }

    @Test
    public void test_deleteComment(){
        User user = new User();
        Post post = new Post();
        user.setUserName("balaji");
        user.setUserPassword("123");
        user.setBlogUserId(2);
        user.setUserMail("abc@gmail.com");
        user.setUserNumber(1);
        user.setGender(GENDER.MALE);
        post.setPostTopic(TOPIC.FOOD);
        post.setPostOwner(user);
        Comment comment= new Comment();
        comment.setCommentId(1);
        comment.setCommentText("hey");
        comment.setCommentedPost(post);
        comment.setCommentedUser(user);
        when(commentRepo.findById(1)).thenReturn(Optional.of(comment));
        doAnswer(Answers.CALLS_REAL_METHODS).when(commentRepo).delete(comment);
        assertThat(commentService.deleteComment(1,2)).isEqualTo("Comment has been removed");

    }

    @Test
    public void test_updateComment(){  //need to check
        User user = new User();
        Post post = new Post();
        user.setUserName("balaji");
        user.setUserPassword("123");
        user.setBlogUserId(2);
        user.setUserMail("abc@gmail.com");
        user.setUserNumber(1);
        user.setGender(GENDER.MALE);
        post.setPostTopic(TOPIC.FOOD);
        post.setPostOwner(user);
        Comment comment= new Comment();
        comment.setCommentId(1);
        comment.setCommentText("hey");
        comment.setCommentedPost(post);
        comment.setCommentedUser(user);
        when(commentRepo.save(comment)).thenReturn(comment);
        when(commentRepo.findById(comment.getCommentedUser().getBlogUserId())).thenReturn(Optional.of(comment));

        assertThat(commentService.updateComments(comment,2)).isEqualTo("Comment has been updated");
    }
}
