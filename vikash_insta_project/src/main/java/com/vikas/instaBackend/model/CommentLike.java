package com.vikas.instaBackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CommentLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentLikeId;

    @ManyToOne
    @JoinColumn(name = "fk_post_id")
    private Post instaPost;


    @OneToOne
    @JoinColumn(name = "fk_comment_id")
    private Comment instaComment;


    @ManyToOne
    @JoinColumn(name = "fk_liker_id")
    private User liker;// exactly the same logic with the commenter








}
