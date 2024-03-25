package org.example.BloggingPlatformApi.ServiceTest;

import org.example.BloggingPlatformApi.Model.AuthenticationToken;
import org.example.BloggingPlatformApi.Model.Enums.GENDER;
import org.example.BloggingPlatformApi.Model.Enums.TOPIC;
import org.example.BloggingPlatformApi.Model.Post;
import org.example.BloggingPlatformApi.Model.User;
import org.example.BloggingPlatformApi.Repository.IAuthenticationRepo;
import org.example.BloggingPlatformApi.Repository.IPostRepo;
import org.example.BloggingPlatformApi.Service.AuthenticationService;
import org.example.BloggingPlatformApi.Service.PostService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PostServiceTest {

    @Mock
    IPostRepo postRepo;

    @InjectMocks
    PostService postService;

    @InjectMocks
    AuthenticationService authService;

    @Mock
    IAuthenticationRepo authRepo;


    @Test
    public void test_addPost(){     //need to check

        User user = new User();
        user.setBlogUserId(10);
        user.setGender(GENDER.MALE);
        user.setUserNumber(1);
        user.setUserName("abc");
        user.setUserMail("abc@gmail.com");
        user.setUserPassword("abc");

        AuthenticationToken authToken = new AuthenticationToken();
        authToken.setAuthUser(user);
        authToken.setTokenValue("abc");
        authToken.setAuthId(1);
        authToken.setAuthCreationStamp(LocalDateTime.now());

        Post post = new Post();
        post.setPostOwner(user);
        post.setPostTopic(TOPIC.FINANCE);
        post.setPostId(1);

 //       when(authService.authenticate("abc@gmail.com","abc")).thenReturn(true);
 //       when(authRepo.findFirstByTokenValue("abc")).thenReturn(authToken);
//        when((authToken.getAuthUser().getUserMail()).equals(user.getUserMail())).thenReturn(true);
        when(authService.authenticate("abc@gmail.com","abc")).thenReturn(true);
        when(postRepo.save(post)).thenReturn(post);

        String result = postService.addPost(post,"abc@gmail.com","abc");
        assertEquals("Post added successfully",result);


    }

    @Test
    public void test_updatePost(){
        User user = new User();
        user.setBlogUserId(10);
        user.setGender(GENDER.MALE);
        user.setUserNumber(1);
        user.setUserName("abc");
        user.setUserMail("abc@gmail.com");
        user.setUserPassword("abc");


        Post post = new Post();
        post.setPostOwner(user);
        post.setPostTopic(TOPIC.FINANCE);
        post.setPostId(1);
        when(postRepo.findById(1)).thenReturn(Optional.of(post));
        when(postRepo.save(post)).thenReturn(post);
        assertEquals("Post has been updated",postService.updatePost(post,10));
    }

    @Test
    public void test_deletePost(){     //need to check
        User user = new User();
        user.setBlogUserId(10);
        user.setGender(GENDER.MALE);
        user.setUserNumber(1);
        user.setUserName("abc");
        user.setUserMail("abc@gmail.com");
        user.setUserPassword("abc");
        AuthenticationToken authToken = new AuthenticationToken();
        authToken.setAuthUser(user);
        authToken.setTokenValue("abc");
        authToken.setAuthId(1);
        authToken.setAuthCreationStamp(LocalDateTime.now());

        Post post = new Post();
        post.setPostOwner(user);
        post.setPostTopic(TOPIC.FINANCE);
        post.setPostId(1);
        when(authRepo.findFirstByTokenValue("abc")).thenReturn(authToken);
        when(authService.authenticate("abc@gmail.com","abc")).thenReturn(true);
        when(postRepo.findById(1)).thenReturn(Optional.of(post));
        doAnswer(Answers.CALLS_REAL_METHODS).when(postRepo).delete(post);
        assertEquals("Post has been deleted",postService.deletePost(1,"abc@gmail.com","abc"));
    }
}
