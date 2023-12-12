package com.vikas.instaBackend.repo;

import com.vikas.instaBackend.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IRepoCommentLike extends JpaRepository<CommentLike,Integer> {


    CommentLike findByInstaCommentAndLiker(Comment instaComment, User liker);


    Integer countByInstaCommentCommentId(Integer commentId);





    CommentLike findByCommentLikeIdAndLiker(Integer likeId, User commentUnLiker);

    List<CommentLike> findByCommentLikeId(Integer commentId);
}
