package com.vikas.instaBackend.service;


import com.vikas.instaBackend.model.Comment;
import com.vikas.instaBackend.model.Like;
import com.vikas.instaBackend.model.Post;
import com.vikas.instaBackend.model.User;
import com.vikas.instaBackend.repo.ILikeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public boolean commentIsAlreadyLiked(Comment instaComment, User liker ){
       Like like = likeRepo.findByInstaCommentAndLiker(instaComment,liker);
       return like!= null;


    }

    public void addLike(Like newLike) {
        likeRepo.save(newLike);
    }

    public String removeLikesByLikerAndPost(User unLiker,Post instaPostToBeUnlinked) {
        List<Like> likes  = likeRepo.findByInstaPostAndLiker(instaPostToBeUnlinked,unLiker);

        likeRepo.deleteAll(likes);
        return "removed like";
    }


    public String removeLikeByLikerAndComment(Integer likeId, User commentUnLiker) {
        Like like = likeRepo.findByLikeIdAndLiker(likeId, commentUnLiker);

        if (like != null) {
            likeRepo.delete(like);
            return "Like removed successfully";
        } else {
            return " like not found with ID: " + likeId;
        }

    }
}
