package org.example.BloggingPlatformApi.Service;

import org.example.BloggingPlatformApi.Model.*;
import org.example.BloggingPlatformApi.Model.Enums.TOPIC;
import org.example.BloggingPlatformApi.Repository.IPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    IPostRepo postRepo;

    @Autowired
    AuthenticationService authenticationService;

    public String addPost(Post post,String email,String authToken){
        boolean isAuthorised = authenticationService.authenticate(email,authToken);
        if(isAuthorised) {
            postRepo.save(post);
            return "Post added successfully";
        }return "Unauthorised user request detected....request denied";
    }
    public List<Post> getAllPosts(){
        return postRepo.findAll();
    }
    public Post getPostById(Integer Id){
        Optional<Post>postOptional = postRepo.findById(Id);
        if(postOptional.isPresent()){
            Post post = postOptional.get();
            return post;
        }return null;
    }

    public String updatePost(Post uPost,Integer currentUserId) {
        Post connectedPost = postRepo.findById(uPost.getPostId()).orElse(null);
        if(connectedPost!=null && connectedPost.getPostOwner().getBlogUserId().equals(currentUserId)){
            connectedPost.setPostTopic(uPost.getPostTopic());
            postRepo.save(connectedPost);
            return "Post has been updated";
        }else if(connectedPost == null){
            return "Post not found";
        }else{
            return "Unauthorised update request detected ...request denied";
        }

    }

    public String deletePost(Integer Id,String email, String token){
        boolean isAuthorised = authenticationService.authenticate(email,token);
        if(isAuthorised) {
            Optional<Post> postOptional = postRepo.findById(Id);
            if (postOptional.isPresent()) {
                Post post = postOptional.get();
                postRepo.delete(post);
                return "Post has been deleted";
            }
            return "Post not found";
        }return "Unauthorised user request detected...request denied";
    }

    public List<Post>getPostByTopic(TOPIC topic){
        return postRepo.findByPostTopic(topic);
    }
}
