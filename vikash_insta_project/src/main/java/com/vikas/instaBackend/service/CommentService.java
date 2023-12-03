package com.vikas.instaBackend.service;


import com.vikas.instaBackend.model.Comment;
import com.vikas.instaBackend.model.Post;
import com.vikas.instaBackend.repo.ICommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {


    @Autowired
    ICommentRepo commentRepo;

    public void clearCommentsByPost(Post myPost) {

        //find all the comments for the myPost

        List<Comment> postComments = commentRepo.findByInstaPost(myPost);

        commentRepo.deleteAll(postComments);

    }

    public void addComment(Comment newComment) {
        newComment.setCommentCreationTimeStamp( LocalDateTime.now());
        commentRepo.save(newComment);
    }

    public Comment getCommentById(Integer commentId) {
        return commentRepo.findById(commentId).orElseThrow();
    }

    public void removeCommentById(Comment commentToBeDeleted) {
        commentRepo.delete(commentToBeDeleted);

    }


    public String getCommentsById(Integer postId) {
        return String.valueOf(commentRepo.countByInstaPostPostId(postId));
    }


    public List<Comment> getActualCommentsByPostId(Integer instPost) {
        return commentRepo.findByInstaPostPostId(instPost);
    }
}
