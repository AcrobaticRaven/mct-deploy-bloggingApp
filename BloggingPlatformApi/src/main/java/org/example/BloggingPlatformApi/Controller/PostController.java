package org.example.BloggingPlatformApi.Controller;

import org.example.BloggingPlatformApi.Model.Post;
import org.example.BloggingPlatformApi.Model.Enums.TOPIC;
import org.example.BloggingPlatformApi.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    PostService postService;


    @PostMapping("Post")
    public String addPost(@RequestBody Post post, @RequestParam String mail, @RequestParam String token){
        return postService.addPost(post,mail,token);
    }

    @GetMapping("Post/Id/{Id}")
    public Post getPostById(@PathVariable Integer Id){
        return postService.getPostById(Id);
    }

    @GetMapping("Posts")
    public List<Post> getAllPosts(){
        return postService.getAllPosts();
    }

    @PutMapping("Post")
    public String updatePost(@RequestBody Post post, @RequestParam Integer currentUserId){
        return postService.updatePost(post,currentUserId);
    }

    @DeleteMapping("Post/{Id}")
    public String deletePost(@PathVariable Integer Id,  @RequestParam String mail, @RequestParam String token){
        return postService.deletePost(Id,mail,token);
    }

    @GetMapping("Post/Topic")
    public List<Post>getPostByTopic(@RequestParam TOPIC topic){
        return postService.getPostByTopic(topic);
    }
}
