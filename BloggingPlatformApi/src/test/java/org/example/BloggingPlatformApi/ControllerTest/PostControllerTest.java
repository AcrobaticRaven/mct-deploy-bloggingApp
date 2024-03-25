
package org.example.BloggingPlatformApi.ControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.BloggingPlatformApi.Controller.PostController;
import org.example.BloggingPlatformApi.Model.AuthenticationToken;
import org.example.BloggingPlatformApi.Model.Enums.TOPIC;
import org.example.BloggingPlatformApi.Model.Post;
import org.example.BloggingPlatformApi.Model.User;
import org.example.BloggingPlatformApi.Service.PostService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(PostController.class)
public class PostControllerTest {

    @Autowired
  private  MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @Test
    public void test_getPostById() throws Exception { //need to check
User user = new User();
user.setBlogUserId(1);
user.setUserMail("abc@gmail.com");
user.setUserNumber(1);
user.setUserName("abc");
user.setUserPassword("123");


      Post post = new Post();
      post.setPostOwner(user);
      post.setPostId(1);
      post.setPostTopic(TOPIC.LIFESTYLE);
      when(postService.getPostById(1)).thenReturn(post);
      mockMvc.perform(MockMvcRequestBuilders.get("/Post/{Id}",1)
                      .contentType(MediaType.APPLICATION_JSON))
              .andExpect(MockMvcResultMatchers.status().isOk());
       //       .andExpect(MockMvcResultMatchers.jsonPath("$.postOwner").value(user));
    }

    @Test
    public void test_getAllPosts() throws Exception {
        User user = new User();
        user.setBlogUserId(1);
        user.setUserMail("abc@gmail.com");
        user.setUserNumber(1234567890);
        user.setUserName("abc");
        user.setUserPassword("123");
        Post post1 = new Post();
        post1.setPostTopic(TOPIC.FOOD);
        post1.setPostOwner(user);
        post1.setPostId(1);
        Post post2 = new Post();
        post2.setPostId(2);
        post2.setPostOwner(user);
        post2.setPostTopic(TOPIC.FINANCE);
        List<Post>postList= new ArrayList<>();
        postList.add(post1);
        postList.add(post2);

        when(postService.getAllPosts()).thenReturn(postList);
        mockMvc.perform(MockMvcRequestBuilders.get("/Posts").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].postId").value(1));
    }

    @Test
    public void test_addPost() throws Exception {

        User user = new User();
        user.setBlogUserId(1);
        user.setUserMail("abc@gmail.com");
        user.setUserNumber(1234567890);
        user.setUserName("abc");
        user.setUserPassword("123");
        AuthenticationToken authToken = new AuthenticationToken();
        authToken.setTokenValue("abc");
        authToken.setAuthCreationStamp(LocalDateTime.now());
        authToken.setAuthId(1);
        authToken.setAuthUser(user);
        Post post1 = new Post();
        post1.setPostTopic(TOPIC.FOOD);
        post1.setPostOwner(user);
        post1.setPostId(1);
        when(postService.addPost(post1,"abc@gmail.com", "abc")).thenReturn("Post added successfully");
        mockMvc.perform(MockMvcRequestBuilders.post("/Post/{mail}/{token}","abc@gmail.com","abc").contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(post1)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void test_updatePost() throws Exception {
        User user = new User();
        user.setBlogUserId(1);
        user.setUserMail("abc@gmail.com");
        user.setUserNumber(1234567890);
        user.setUserName("abc");
        user.setUserPassword("123");
        Post post = new Post();
        post.setPostTopic(TOPIC.FOOD);
        post.setPostOwner(user);
        post.setPostId(1);
        Post post1 = new Post();
        post1.setPostTopic(TOPIC.FINANCE);
        post1.setPostOwner(user);
        post1.setPostId(1);
        when(postService.getPostById(1)).thenReturn(post);
        when(postService.updatePost(post1,1)).thenReturn("Post has been updated");
        mockMvc.perform(MockMvcRequestBuilders.put("/Post/{currentUserId}",1).contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(post1)))
                .andExpect(MockMvcResultMatchers.status().isOk());


    }

    @Test
    public void test_deletePost() throws Exception {
        User user = new User();
        user.setBlogUserId(1);
        user.setUserMail("abc@gmail.com");
        user.setUserNumber(1);
        user.setUserName("abc");
        AuthenticationToken authToken = new AuthenticationToken();
        authToken.setTokenValue("abc");
        authToken.setAuthCreationStamp(LocalDateTime.now());
        authToken.setAuthId(1);
        authToken.setAuthUser(user);
        user.setUserPassword("123");
        Post post = new Post();
        post.setPostTopic(TOPIC.FOOD);
        post.setPostOwner(user);
        post.setPostId(1);
        when(postService.deletePost(1,"abc@gmail.com","abc")).thenReturn("Post has been deleted");
        mockMvc.perform(MockMvcRequestBuilders.delete("/Post/{Id}/{mail}/{token}",1,"abc@gmail.com","abc")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void test_getPostByTopic() throws Exception {
        User user = new User();
        user.setBlogUserId(1);
        user.setUserMail("abc@gmail.com");
        user.setUserNumber(1);
        user.setUserName("abc");
        Post post = new Post();
        post.setPostTopic(TOPIC.FOOD);
        post.setPostOwner(user);
        post.setPostId(1);
        Post post1 = new Post();
        post1.setPostId(2);
        post1.setPostOwner(user);
        post1.setPostTopic(TOPIC.FOOD);
        List<Post>postList = new ArrayList<>();
        postList.add(post);
        postList.add(post1);
        when(postService.getPostByTopic(TOPIC.FOOD)).thenReturn(postList);
        mockMvc.perform(MockMvcRequestBuilders.get("/PostByTopic/{topic}",TOPIC.FOOD).contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

}
