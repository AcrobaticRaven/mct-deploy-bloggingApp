package org.example.BloggingPlatformApi.Controller;

import org.example.BloggingPlatformApi.Model.Comment;
import org.example.BloggingPlatformApi.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("Comment")
    public String addComment(@RequestBody Comment comment){
        return commentService.addComment(comment);
    }

    @GetMapping("Comment/{Id}")
    public Comment getCommentById(@PathVariable Integer Id){
        return commentService.getCommentById(Id);
    }

    @GetMapping("Comments")
    public List<Comment>getAllComments(){
        return commentService.getAllComments();
    }

    @PutMapping("Comment")
    public String updateComment(@RequestBody Comment comment,@RequestParam Integer ownerId){
        return commentService.updateComments(comment,ownerId);
    }

    @DeleteMapping("Comment/{Id}/{currentUserId}")
    public String deleteComment(@PathVariable Integer Id, @PathVariable Integer currentUserId){
        return commentService.deleteComment(Id,currentUserId);
    }

    @GetMapping("Comments/{postId}")
    public List<Comment>getCommentsByPost(@PathVariable Integer postId){
        return commentService.getCommentsByPost(postId);
    }

    @GetMapping("Comments/commentedTime")
    public List<Comment>sortByCommentedTime(){
       return commentService.sortCommentsByTime();
    }
}
