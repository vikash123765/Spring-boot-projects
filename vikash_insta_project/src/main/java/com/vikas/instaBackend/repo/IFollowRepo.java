package com.vikas.instaBackend.repo;

import com.vikas.instaBackend.model.Follow;
import com.vikas.instaBackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFollowRepo extends JpaRepository<Follow,Integer> {


    Follow findByCurrentUserAndCurrentUserFollower(User userToFollow, User follower);
    



    int countByCurrentUserFollower(User target);

    int countByCurrentUser(User target);
}
