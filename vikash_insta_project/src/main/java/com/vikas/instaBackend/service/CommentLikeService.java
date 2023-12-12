package com.vikas.instaBackend.service;

import com.vikas.instaBackend.model.*;
import com.vikas.instaBackend.repo.IRepoCommentLike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentLikeService {

    @Autowired
    IRepoCommentLike commentLikeRepo;






    public void addCommentLike(CommentLike newCommentLike) {
        commentLikeRepo.save(newCommentLike);
    }

    public boolean commentIsAlreadyLiked(Comment instaComment, User liker ){
        CommentLike commentLike = commentLikeRepo.findByInstaCommentAndLiker(instaComment,liker);
        return  commentLike!= null;


    }

    public String removeLikeByLikerAndComment(Integer likeId, User commentUnLiker) {
            CommentLike CommentLike = commentLikeRepo.findByCommentLikeIdAndLiker(likeId, commentUnLiker);

            if (CommentLike != null) {
                commentLikeRepo.delete(CommentLike);
                return "Like removed successfully";
            } else {
                return " like not found with ID: " + likeId;
            }

        }

    public String getLikesByCommentId(Integer commentId) {
            return String.valueOf(commentLikeRepo.countByInstaCommentCommentId(commentId));
        }

    public List<String> getActualLikesByCommentId(Integer commentId) {
        // getting likes from likerepo based on post commentId
        List<CommentLike> likes = commentLikeRepo.findByCommentLikeId(commentId);
        // creating list to store the users
        List<String> userLikers = new ArrayList<>();

        // iterate once over all the likes
        for (CommentLike commentLike : likes) {
            // Check for null values before accessing properties
            if (commentLike.getInstaComment() != null && commentLike.getInstaPost() != null && commentLike.getLiker() != null && commentLike.getLiker().getUserName() != null) {
                // Getting user from each like and adding to the list
                userLikers.add(commentLike.getLiker().getUserName());
            }
        }
        // returning result
        return userLikers;
    }


}


