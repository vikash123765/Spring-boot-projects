package com.vikas.instaBackend.repo;

import com.vikas.instaBackend.model.Comment;
import com.vikas.instaBackend.model.Like;
import com.vikas.instaBackend.model.Post;
import com.vikas.instaBackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ILikeRepo extends JpaRepository<Like,Integer> {


    List<Like> findByInstaPost(Post myPost);

    List<Like> findByInstaPostAndLiker(Post instaPost, User liker);

    Like findByInstaCommentAndLiker(Comment instaComment, User liker);


    Like findByLikeIdAndLiker(Integer likeId, User commentUnLiker);




    long countByInstaCommentCommentId(Integer commentId);


    List<Like> findByInstaPostPostId(Integer postId);
}
