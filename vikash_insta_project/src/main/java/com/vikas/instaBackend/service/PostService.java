package com.vikas.instaBackend.service;


import com.vikas.instaBackend.model.Post;
import com.vikas.instaBackend.repo.IPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PostService {

    @Autowired
    IPostRepo postRepo;

    @Autowired
    LikeService likeService;

    @Autowired
    CommentService commentService;

    public void createInstaPost(Post instaPost) {
        instaPost.setPostCreatedTimeStamp(LocalDateTime.now());
        postRepo.save(instaPost);
    }

    public Post getPostById(Integer postId) {
        return  postRepo.findById(postId).orElseThrow();
    }

    public void removeById(Integer postId) {

        Post myPost = postRepo.findById(postId).orElseThrow();

        //remove all likes for this post
        likeService.clearLikesByPost(myPost);

        //remove all comments for this post
        commentService.clearCommentsByPost(myPost);


        //finally remove this post as no table records are dependent on this
        postRepo.delete(myPost);

    }

    public String getLikesForPost(Integer postId) {

        Post instaPost = postRepo.findById(postId).orElseThrow();

        return likeService.getLikesByPost(instaPost);
    }
}
