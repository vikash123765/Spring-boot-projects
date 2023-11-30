package com.vikas.instaBackend.service;

import com.vikas.instaBackend.model.AuthenticationToken;
import com.vikas.instaBackend.repo.IAuthenticationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationService {

    @Autowired
    IAuthenticationRepo authenticationRepo;

    public void createToken(AuthenticationToken token) {

        authenticationRepo.save(token);
    }

    public boolean authenticate(String email,String tokenValue) {

        //find user from the token value and get the email
        AuthenticationToken tokenObj =  authenticationRepo.findByTokenValue(tokenValue);

        if(tokenObj != null)
        {
            String tokenUserEmail = tokenObj.getUser().getUserEmail();
            //compare the 2 users email that u found above

            return email.equals(tokenUserEmail);
        }

        return false;

    }

    public void deleteToken(String tokenValue) {

        AuthenticationToken authToken = authenticationRepo.findByTokenValue(tokenValue);
        authenticationRepo.delete(authToken);
    }
}
