package org.example.BloggingPlatformApi.Service;

import org.example.BloggingPlatformApi.Model.Comment;
import org.example.BloggingPlatformApi.Repository.ICommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    ICommentRepo commentRepo;


    public String addComment(Comment comment){
        commentRepo.save(comment);
        return "Comment has been added successfully";
    }
    public String deleteComment(Integer commentedId,Integer currentUserId){
        Optional<Comment>commentOptional = commentRepo.findById(commentedId);
        if(commentOptional.isPresent()){
            Comment comment = commentOptional.get();
            Integer postOwnerId = comment.getCommentedPost().getPostOwner().getBlogUserId();
            Integer commentOwnerId = comment.getCommentedUser().getBlogUserId();
            if(commentOwnerId.equals(currentUserId)||(postOwnerId.equals(currentUserId))) {
                commentRepo.delete(comment);
                return "Comment has been removed";
            }return "Unauthorised delete request detected...request denied";
        }return "Comment not found";
    }
    public String updateComments(Comment comment,Integer ownerId){
        Integer connectedId = comment.getCommentedUser().getBlogUserId();
        if(connectedId.equals(ownerId)) {
            Optional<Comment> commentOptional = commentRepo.findById(comment.getCommentId());
            if (commentOptional.isPresent()) {
                Comment comment1 = commentOptional.get();
                comment1.setCommentText(comment.getCommentText());
                commentRepo.save(comment1);
                return "Comment has been updated";
            }
            return "Comment not found";
        }return "Unauthorised update request detected...request denied";
    }

    public List<Comment> getAllComments(){
        return commentRepo.findAll();
    }

    public Comment getCommentById(Integer Id){
        return commentRepo.findById(Id).orElse(null);
    }

    public List<Comment>getCommentsByPost(Integer Id){
        List<Comment>commentList = new ArrayList<>();
        for(Comment c : commentRepo.findAll()){
            if(c.getCommentedPost().getPostId().equals(Id)){
                commentList.add(c);
            }
        }if(commentList.size()>10){
          List<Comment>clist = new ArrayList<>();
          for(Comment x : commentList){
              clist.add(x);
              if(clist.size()==10){
                  return clist;
              }
          }

        }return commentList;
    }


}
