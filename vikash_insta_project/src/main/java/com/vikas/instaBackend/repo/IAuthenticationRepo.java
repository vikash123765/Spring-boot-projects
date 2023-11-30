package com.vikas.instaBackend.repo;

import com.vikas.instaBackend.model.AuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthenticationRepo extends JpaRepository<AuthenticationToken,Long> {

    AuthenticationToken findByTokenValue(String tokenValue);
}
