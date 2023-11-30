package com.vikas.instaBackend.repo;

import com.vikas.instaBackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepo extends JpaRepository<User,Integer> {

    User findByUserEmail(String newEmail);
}
