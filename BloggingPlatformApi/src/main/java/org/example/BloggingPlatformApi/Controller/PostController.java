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


    @PostMapping("Post/{mail}/{token}")
    public String addPost(@RequestBody Post post, @PathVariable String mail, @PathVariable String token){
        return postService.addPost(post,mail,token);
    }

    @GetMapping("Post/{Id}")
    public Post getPostById(@PathVariable Integer Id){
        return postService.getPostById(Id);
    }

    @GetMapping("Posts")
    public List<Post> getAllPosts(){
        return postService.getAllPosts();
    }

    @PutMapping("Post/{currentUserId}")
    public String updatePost(@RequestBody Post post, @PathVariable Integer currentUserId){
        return postService.updatePost(post,currentUserId);
    }

    @DeleteMapping("Post/{Id}/{mail}/{token}")
    public String deletePost(@PathVariable Integer Id,  @PathVariable String mail, @PathVariable String token){
        return postService.deletePost(Id,mail,token);
    }

    @GetMapping("PostByTopic/{topic}")
    public List<Post>getPostByTopic(@PathVariable TOPIC topic){
        return postService.getPostByTopic(topic);
    }
}
