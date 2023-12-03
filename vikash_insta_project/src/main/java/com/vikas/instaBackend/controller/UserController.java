package com.vikas.instaBackend.controller;


import com.vikas.instaBackend.model.Post;
import com.vikas.instaBackend.model.User;
import com.vikas.instaBackend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Validated
@RestController
public class UserController {

    @Autowired
    UserService userService;

    //user sign up
    @PostMapping("user/signup")
    public String userSignUp(@Valid @RequestBody User newUser)
    {

        return userService.userSignUp(newUser);
    }

    //user sign in

    @PostMapping("user/signIn/{email}/{password}")
    public String userSignIn(@PathVariable String email, @PathVariable String password)
    {
        return userService.userSignIn(email,password);
    }



    //user sign out

    @DeleteMapping("user/signOut")
    public String userSignOut(@RequestParam String email, @RequestParam String token)
    {
        return userService.userSignOut(email,token);
    }

    //create post
    @PostMapping("InstaPost")
    public String createInstaPost(@RequestParam String email,@RequestParam String tokenValue, @RequestBody Post instaPost)
    {
        return userService.createInstaPost(email,tokenValue,instaPost);
    }


    //delete post
    @DeleteMapping("InstaPost/{postId}")
    public String deleteInstaPost(@RequestParam String email, @RequestParam String tokenValue, @PathVariable Integer postId)
    {
        return userService.deleteInstaPost(email,tokenValue,postId);
    }

    // add like for a perticual post

    @PostMapping("like/post/{postId}")
    public String addLike(@RequestParam String email, @RequestParam String tokenValue, @PathVariable Integer postId){
        return userService.addLike(email,tokenValue,postId);
    }

    // unlike a perticual post

    @DeleteMapping("unlike/post/{postId}")

    public String removeLike(@RequestParam String email, @RequestParam String tokenValue, @PathVariable Integer postId){
        return userService.removeLike(email,tokenValue,postId);
    }

    //get how many  likes for a particular post:

    @GetMapping("count/likes/post/{postId}")
    public String getLikesByPostId(@PathVariable Integer postId)
    {
        return userService.getLikesByPostId(postId);
    }

    //get how many  likes for a particular comment:
    @GetMapping("count/likes/comment/{commentId}")
    public String getLikesByCommentId(@PathVariable Integer commentId)
    {
        return userService.getLikesByCommentId(commentId);
    }



    //get how many  comments for a particular post:
    @GetMapping("count/comments/post/{postId}")
    public String getCommentsByPostId(@PathVariable Integer postId)
    {
        return userService.getCommentsByPostIdCount(postId);
    }

    // get actual comments for the post

    @GetMapping("comments/post/{postId}")
    public List<Map<String, Object>> getActualCommentsByPostId(@PathVariable Integer postId) {
        return userService.getActualCommentsByPostId(postId);
    }

    // get actual likes for the post

    @GetMapping("likes/post/{postId}")
    public List<String> getActualLikesByPostId(@PathVariable Integer postId) {
        return userService.getActualLikesByPostId(postId);
    }

    // like a comment
    @PostMapping("like/comment/{commentId}")
    public String addCommentLike(@RequestParam String email, @RequestParam String tokenValue, @PathVariable Integer commentId,Integer postId){
        return userService.addCommentLike(email,tokenValue,commentId,postId);
    }
    // unlike a perticual comment

  @DeleteMapping("unlike/comment/{likeId}")

    public String removeCommentLike(@RequestParam String email, @RequestParam String tokenValue, @PathVariable Integer likeId) {
      return userService.removeCommentLike(email, tokenValue, likeId);
  }


    // delete perticular comment
    @DeleteMapping("post/comment/{commentIds}")
    public String removeComment(@RequestParam String email, @RequestParam String tokenValue, @PathVariable Integer commentId)
    {
        return userService.removeComment(email,tokenValue,commentId);
    }
    // comment on a post
    @PostMapping("comment/post/{postId}")
    public String addComment(@RequestParam String email, @RequestParam String tokenValue, @PathVariable Integer postId,@RequestBody String commentBody)
    {
        return userService.addComment(email,tokenValue,commentBody,postId);
    }





    // follow
    @PostMapping("user/{userId}/follower/{followerId}")
    public String follow(@RequestParam String email, @RequestParam String tokenValue,@PathVariable Integer userId,@PathVariable Integer  followerId){
        return userService.follow(email,tokenValue,userId,followerId);
    }


    // unfollow
    @DeleteMapping("unFollow/{userId}/followerId{followerId}")

    public String unFollowUser(@RequestParam String email, @RequestParam String tokenValue, @PathVariable Integer userId,@PathVariable Integer followerId){
        return userService.removeFollow(email,tokenValue,userId,followerId);
    }

    // is following or not

    @GetMapping("user/{userId}/isFollowingOrNot/followerId{followerId}")

    public String followOrNot( @PathVariable Integer userId,@PathVariable Integer followerId){
        return userService.followOrNot(userId,followerId);
    }






}
