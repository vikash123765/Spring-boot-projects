package com.vikas.instaBackend.repo;

import com.vikas.instaBackend.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPostRepo extends JpaRepository<Post,Integer> {
}
