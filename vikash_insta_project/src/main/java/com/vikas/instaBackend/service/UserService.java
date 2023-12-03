package com.vikas.instaBackend.service;


import com.vikas.instaBackend.model.*;
import com.vikas.instaBackend.repo.ICommentRepo;
import com.vikas.instaBackend.repo.IUserRepo;
import com.vikas.instaBackend.service.EmailUtility.MailHandlerBase;
import com.vikas.instaBackend.service.HashingUtility.PasswordEncryptor;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
public class UserService {

    @Autowired
    IUserRepo userRepo;
    @Autowired
    ICommentRepo commentRepo;
    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    PostService postService;

    @Autowired
    LikeService likeService;

    @Autowired
    CommentService commentService;

    @Autowired
    FollowService followService;


    public String userSignUp(User newUser) {

        //check if already exist -> Not allowed : try logging in

        String newEmail = newUser.getUserEmail();

        User existingUser = userRepo.findByUserEmail(newEmail);

        if (existingUser != null) {
            return "email already in use";
        }

        // passwords are encrypted before we store it in the table

        String signUpPassword = newUser.getUserPassword();

        //hashing logic to convert the password into some hashed password to be stored in the table

        try {
            String encryptedPassword = PasswordEncryptor.encrypt(signUpPassword);

            newUser.setUserPassword(encryptedPassword);
            newUser.setBlueTick(false);


            userRepo.save(newUser);
            return "Insta user registered!!!";


        } catch (NoSuchAlgorithmException e) {

            return "Internal Server issues while saving password, try again later!!!";
        }

    }

    public String userSignIn(String email, String password) {

        //check if the email is there in your tables-- this should be the case !!

        User existingUser = userRepo.findByUserEmail(email); //db call

        if (existingUser == null) {
            return "Not a valid email, Please sign up first !!!";
        }

        //password should be matched

        try {
            String encryptedPassword = PasswordEncryptor.encrypt(password);

            if (existingUser.getUserPassword().equals(encryptedPassword)) {
                //login should be allowed using token
                AuthenticationToken token = new AuthenticationToken(existingUser);

                if (MailHandlerBase.sendEmail(email, "otp after login", token.getTokenValue())) {

                    authenticationService.createToken(token);
                    return "check email for otp/token!!!";
                } else {
                    return "error while generating token!!!";
                }


            } else {
                //password was wrong!!!
                return "Invalid Credentials!!!";
            }

        } catch (NoSuchAlgorithmException e) {

            return "Internal Server issues while saving password, try again later!!!";
        }

    }

    public String userSignOut(String email, String tokenValue) {

        if (authenticationService.authenticate(email, tokenValue)) {

            //delete the token row
            authenticationService.deleteToken(tokenValue);
            return "Sign Out successful!!";
        } else {
            return "Un Authenticated access!!!";
        }

    }

    public String createInstaPost(String email, String tokenValue, Post instaPost) {

        if (authenticationService.authenticate(email, tokenValue)) {

            // insta post for this particular logged in user :

            User existingUser = userRepo.findByUserEmail(email);
            instaPost.setPostOwner(existingUser);

            //save the instagram post
            postService.createInstaPost(instaPost);
            return instaPost.getPostType() + " posted!!";

        } else {
            return "Un Authenticated access!!!";
        }

    }

    public String deleteInstaPost(String email, String tokenValue, Integer postId) {

        if (authenticationService.authenticate(email, tokenValue)) {

            Post instaPost = postService.getPostById(postId);

            //
            if (instaPost.getPostOwner().getUserEmail().equals(email)) {
                //finally delete the insta post
                postService.removeById(postId);
                return "post removed!!";
            } else {
                return "Un authorized access!!";
            }

        } else {
            return "Un Authenticated access!!!";
        }

    }

    public String getLikesByPostId( Integer postId) {

        return postService.getLikesForPost(postId);

    }

    public String addLike(String email, String tokenValue, Integer postId) {
        if (authenticationService.authenticate(email, tokenValue)) {
            // figure out the actual post
            Post instaPost = postService.getPostById(postId);
            // figure out the user- corresponding to th emil -> liker
            User liker = userRepo.findByUserEmail(email);

            // first check whether this liker has already liked this insta post
            boolean alreadyLiked = likeService.isAlreadyLiked(instaPost, liker);

            if (!alreadyLiked) {
                Like newLike = new Like(null, instaPost, null, liker);
                likeService.addLike(newLike);
                return liker.getUserHandle() + " like post: " + postId;
            } else {
                return "already liked";
            }
        } else {
            return "Un Authenticated access!!!";
        }
    }

    public String removeLike(String email, String tokenValue, Integer postId) {
        if (authenticationService.authenticate(email, tokenValue)) {
            // figure out the actual post
            Post instaPostToBeUnlinked = postService.getPostById(postId);
            // figure out the user- corresponding to th emil -> liker
            User unLiker = userRepo.findByUserEmail(email);

            return likeService.removeLikesByLikerAndPost(unLiker, instaPostToBeUnlinked);

        } else {
            return "Un Authenticated access!!";
        }
    }

    public String addComment(String email, String tokenValue, String commentBody, Integer postId) {
        if (authenticationService.authenticate(email, tokenValue)) {
            // figure out the actual post
            Post instaPost = postService.getPostById(postId);
            // figure out the user- corresponding to th emil -> liker
            User commenter = userRepo.findByUserEmail(email);


            Comment newComment = new Comment(null, commentBody, null, instaPost, commenter);
            commentService.addComment(newComment);
            return commenter.getUserHandle() + "commented on" + postId;
        } else {
            return "Un Authenticated access!!!";
        }
    }


    public String removeComment(String email, String tokenValue, Integer commentId) {
        if (authenticationService.authenticate(email, tokenValue)) {
            // figure out the comment by comment id
            Comment commentToBeDeleted = commentService.getCommentById(commentId);

            commentService.removeCommentById(commentToBeDeleted);
            return commentToBeDeleted.getCommentBody() + "was deleted";

        } else {
            return "Un Authenticated access!!";
        }
    }

    public String getCommentsByPostId(Integer postId) {

        return commentService.getCommentsById(postId);
    }

    public String addCommentLike(String email, String tokenValue, Integer commentId, Integer postId) {
        if (authenticationService.authenticate(email, tokenValue)) {
            // figure out the comment by comment id
            Comment commentToBeLiked = commentService.getCommentById(commentId);
            User commentLiker = userRepo.findByUserEmail(email);
            Post instaPost = postService.getPostById(postId);


            // first check whether this liker has already liked this insta commemt
            boolean alreadyLiked = likeService.commentIsAlreadyLiked(commentToBeLiked, commentLiker);

            if (!alreadyLiked) {
                Like newLike = new Like(null, instaPost, commentToBeLiked, commentLiker);
                likeService.addLike(newLike);
                return commentLiker.getUserHandle() + " liked comment: " + commentId;
            } else {
                return "already liked";
            }
        } else {
            return "Un Authenticated access!!!";
        }
    }

    public String removeCommentLike(String email, String tokenValue, Integer likeId) {
        if (authenticationService.authenticate(email, tokenValue)) {
            User commentUnLiker = userRepo.findByUserEmail(email);
            return likeService.removeLikeByLikerAndComment(likeId, commentUnLiker);

        } else {
            return "Un Authenticated access!!";
        }
    }

    public String follow(String email, String tokenValue, Integer userId, Integer followerId) {

        if (authenticationService.authenticate(email, tokenValue)) {
            // figure the user that wants to follow
            User follower = userRepo.findById(userId).orElseThrow();
            //  figure out the user to be followed
            User userToFollow = userRepo.findById(followerId).orElseThrow();

            // first check whether this follower  is already following userToFollow
            boolean alreadyFollowing = followService.isAlreadyFollowing(follower, userToFollow);

            if (!alreadyFollowing) {
                Follow newFollow = new Follow(null, follower, userToFollow);
                followService.follow(newFollow);
                return follower.getUserHandle() + " is now succesfully following: " + userToFollow.getUserHandle();
            } else {
                return "already following";
            }
        } else {
            return "Un Authenticated access!!!";
        }
    }


    public String removeFollow(String email, String tokenValue, Integer userId, Integer followerId) {
        if (authenticationService.authenticate(email, tokenValue)) {
            // figure the user that wants to follow
            User follower = userRepo.findById(userId).orElseThrow();
            // figure out the user to be followed
            User userToFollow = userRepo.findById(followerId).orElseThrow();

            boolean alreadyFollowing = followService.isAlreadyFollowing(follower, userToFollow);

            if (alreadyFollowing) {
                followService.removeFollow(follower, userToFollow);
                return "User unfollowed successfully";
            } else {
                return "You are not following this user";
            }
        } else {
            return "Unauthenticated access!!!";
        }

    }

    public String followOrNot( Integer userId, Integer followerId) {

        // figure the user that wants to follow
        User follower = userRepo.findById(userId).orElseThrow();
        // figure out the user to be followed
        User userToFollow = userRepo.findById(followerId).orElseThrow();

        boolean alreadyFollowing = followService.isAlreadyFollowing(follower, userToFollow);
        if (alreadyFollowing) {
            return follower.getUserBio() +" is following " + userToFollow.getUserName();
        } else {
            return follower.getUserBio() +" is not following " + userToFollow.getUserName();
        }

    }

    public String getCommentsByPostIdCount(Integer postId) {

        return commentService.getCommentsById(postId);


    }

    public String getLikesByCommentId(Integer commentId) {
        return likeService.getLikesByCommentId(commentId);
    }

    public List<Map<String, Object>> getActualCommentsByPostId(Integer postId) {
        // get all the comments based on postId
        List<Comment> comments = commentRepo.findByInstaPostPostId(postId);

        // create a list where to store the result in
        List<Map<String, Object>> commentList = new ArrayList<>();

        // iterate once each comment in comments
        for (Comment comment : comments) {
            // create hashmap which will be a single comment
            Map<String, Object> commentMap = new HashMap<>();

            //populate map with username and commentbody fields via put  where key will be string representing the actual value
            // via comment object vi can acess the required fields
            commentMap.put("username", comment.getCommenter().getUserName());
            commentMap.put("commentBody", comment.getCommentBody());
            // add the hashmap to the result list
            commentList.add(commentMap);
        }
        // return result list
        return commentList;
    }


    public List<String> getActualLikesByPostId(Integer postId) {
        return likeService.getActualLikesByPostId(postId);
    }
}
