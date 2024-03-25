
package org.example.BloggingPlatformApi.ControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.BloggingPlatformApi.Controller.CommentController;
import org.example.BloggingPlatformApi.Model.Comment;
import org.example.BloggingPlatformApi.Model.Enums.GENDER;
import org.example.BloggingPlatformApi.Model.Enums.TOPIC;
import org.example.BloggingPlatformApi.Model.Post;
import org.example.BloggingPlatformApi.Model.User;
import org.example.BloggingPlatformApi.Service.CommentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CommentController.class)
public class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentService commentService;


    @Test
    public void createComment_whenMethod() throws Exception{
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
  /*      given(commentService.addComment(comment)).willReturn(String.valueOf(comment));
        mockMvc.perform(post("/Comment")
                        .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(comment)))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$").value("Comment has been added successfully"));
*/
  /*      mockMvc.perform(MockMvcRequestBuilders.post("/comment").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.commentId").exists());
     */


                when(commentService.addComment(comment)).thenReturn("Comment has been added successfully");
                mockMvc.perform(MockMvcRequestBuilders.post("/Comment").contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(comment)))
                        .andExpect(status().isOk());

    }


    @Test
    public void test_getCommentById() throws Exception {
        User user = new User();
        Post post = new Post();
        user.setUserName("balaji");
        user.setUserPassword("123");
        user.setGender(GENDER.MALE);
        user.setUserMail("abc@gmail.com");
        user.setUserNumber(1);
        post.setPostTopic(TOPIC.FOOD);
        post.setPostOwner(user);
        Comment comment= new Comment();
        comment.setCommentId(1);
        comment.setCommentText("hey");
        comment.setCommentedPost(post);
        comment.setCommentedUser(user);
        when(commentService.getCommentById(1)).thenReturn(comment);
        mockMvc.perform(MockMvcRequestBuilders.get("/Comment/{Id}",1).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void test_getAllComments() throws Exception {
        User user = new User();
        Post post = new Post();
        user.setUserName("balaji");
        user.setUserPassword("123");
        user.setUserMail("abc@gmail.com");
        user.setUserNumber(1);
        user.setGender(GENDER.MALE);
        post.setPostTopic(TOPIC.FOOD);
        post.setPostOwner(user);
        List<Comment>commentList = Arrays.asList(new Comment(1,"Hi", LocalDateTime.now(),post,user),(new Comment(2,"Hello",LocalDateTime.now(),post,user)));
         when(commentService.getAllComments()).thenReturn(commentList);
         mockMvc.perform(MockMvcRequestBuilders.get("/Comments").contentType(MediaType.APPLICATION_JSON))
                 .andExpect(status().isOk())
                 .andExpect(jsonPath("$[0].commentText").value("Hi"));
    }

    @Test
    public void test_deleteComment() throws Exception {
        User user = new User();
        Post post = new Post();
        user.setUserName("balaji");
        user.setUserPassword("123");
        user.setGender(GENDER.MALE);
        user.setUserMail("abc@gmail.com");
        user.setUserNumber(1);
        user.setBlogUserId(2);
        post.setPostTopic(TOPIC.FOOD);
        post.setPostOwner(user);
        Comment comment = new Comment();
        comment.setCommentId(4);
        comment.setCommentText("Yo");
        comment.setCommentedPost(post);
        comment.setCommentedUser(user);
        when(commentService.deleteComment(4,2)).thenReturn("Comment has been removed");
        mockMvc.perform(MockMvcRequestBuilders.delete("/Comment/{Id}/{currentUserId}",4,2).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void test_getCommentsByPost() throws Exception {
        User user = new User();
        Post post = new Post();
        user.setUserName("balaji");
        user.setUserPassword("123");
        user.setUserMail("abc@gmail.com");
        user.setUserNumber(1);
        user.setGender(GENDER.MALE);
        user.setBlogUserId(2);
        post.setPostTopic(TOPIC.FOOD);
        post.setPostOwner(user);
        post.setPostId(3);
        List<Comment>commentList = Arrays.asList(new Comment(1,"Hey",LocalDateTime.now(),post,user),new Comment(3,"Yo",LocalDateTime.now(),post,user));
        when(commentService.getCommentsByPost(3)).thenReturn(commentList);
        mockMvc.perform(MockMvcRequestBuilders.get("/Comments/{postId}",3).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }






}

