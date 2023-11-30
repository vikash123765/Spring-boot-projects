package com.vikas.instaBackend.service;

import com.vikas.instaBackend.model.Comment;
import com.vikas.instaBackend.model.Follow;
import com.vikas.instaBackend.model.Like;
import com.vikas.instaBackend.model.User;
import com.vikas.instaBackend.repo.IFollowRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowService {

    @Autowired
    IFollowRepo iFollowRepo;



    public void follow(Follow newFollow) {
        iFollowRepo.save(newFollow);
    }

    public boolean isAlreadyFollowing(User follower, User userToFollow) {
        Follow follow= iFollowRepo.findByCurrentUserAndCurrentUserFollower(follower,userToFollow);
        return follow!= null;
    }

    public void removeFollow(User follower, User userToFollow) {
        Follow follow= iFollowRepo.findByCurrentUserAndCurrentUserFollower(follower,userToFollow);
        iFollowRepo.delete(follow);

    }
}

