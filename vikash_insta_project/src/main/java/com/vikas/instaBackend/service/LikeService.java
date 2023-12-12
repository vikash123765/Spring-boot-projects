package com.vikas.instaBackend.service;


import com.vikas.instaBackend.model.Comment;
import com.vikas.instaBackend.model.Like;
import com.vikas.instaBackend.model.Post;
import com.vikas.instaBackend.model.User;
import com.vikas.instaBackend.repo.ILikeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LikeService {

    @Autowired
    ILikeRepo likeRepo;

    public void clearLikesByPost(Post myPost) {

        //find all the likes for the myPost

        List<Like> postLikes  = likeRepo.findByInstaPost(myPost);

        //remove the likes
        likeRepo.deleteAll(postLikes);


    }

    public String getLikesByPost(Post instaPost) {

       return String.valueOf(likeRepo.findByInstaPost(instaPost).size());

    }

    public boolean isAlreadyLiked(Post instaPost, User liker ){
        List<Like> likes = likeRepo.findByInstaPostAndLiker(instaPost,liker);
        if (likes != null && likes.size() !=0){
            return true;
        }else{
            return false;
        }


    }


    public void addLike(Like newLike) {
        likeRepo.save(newLike);
    }

    public String removeLikesByLikerAndPost(User unLiker,Post instaPostToBeUnlinked) {
        List<Like> likes  = likeRepo.findByInstaPostAndLiker(instaPostToBeUnlinked,unLiker);

        likeRepo.deleteAll(likes);
        return "removed like";
    }







    public List<String> getActualLikesByPostId(Integer postId) {

        // getting likes from likerepo based on post id
            List<Like> likes = likeRepo.findByInstaPostPostId(postId);
            // creating list to store the users
            List<String> userLikers = new ArrayList<>();

            // iterate once over all the likes
        for (Like like : likes) {
            // Check for null values before accessing properties
            if (like.getInstaPost() != null && like.getLiker() != null && like.getLiker().getUserName() != null) {
                // Getting user from each like and adding to the list
                userLikers.add(like.getLiker().getUserName());
            }
        }
            // returning result
            return userLikers;
        }


}
