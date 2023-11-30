package com.vikas.instaBackend.repo;

import com.vikas.instaBackend.model.Follow;
import com.vikas.instaBackend.model.Like;
import com.vikas.instaBackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFollowRepo extends JpaRepository<Follow,Integer> {


    Follow findByCurrentUserAndCurrentUserFollower(User follower, User userToFollow);
}
