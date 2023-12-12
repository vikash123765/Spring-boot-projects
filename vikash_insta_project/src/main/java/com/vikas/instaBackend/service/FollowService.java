package com.vikas.instaBackend.service;

import com.vikas.instaBackend.model.Follow;
import com.vikas.instaBackend.model.User;
import com.vikas.instaBackend.repo.IFollowRepo;
import com.vikas.instaBackend.repo.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowService {

    @Autowired
    IFollowRepo iFollowRepo;

    @Autowired
    IUserRepo userRepo;




    public void startFollowing(User follower, User target) {
        Follow follow = new Follow(null,follower,target);
        iFollowRepo.save(follow);
    }



    public void removeFollow(User follower, User userToFollow) {
        Follow follow= iFollowRepo.findByCurrentUserAndCurrentUserFollower(userToFollow,follower);
        iFollowRepo.delete(follow);

    }

    public boolean isAlreadyFollowing(User follower, User userToFollow) {
        Follow follow = iFollowRepo.findByCurrentUserAndCurrentUserFollower(userToFollow,follower);
        return follow != null && !follower.equals(userToFollow);
    }

    public String getFollowsAndFollowingByUserId(Integer userId) {

           User target = userRepo.findById(userId).orElseThrow();

            int followersCount = iFollowRepo.countByCurrentUserFollower(target);
            int followingCount = iFollowRepo.countByCurrentUser(target);

            return  target.getUserName() + " has " + followersCount + " followers and is following " + followingCount + " users.";
        }

}

