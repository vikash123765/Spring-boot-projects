package com.vikas.instaBackend.model;

import com.vikas.instaBackend.model.enums.PostType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;
    private String postContent;// we will keep a url in this field
    private String postCaption;
    private String postLocation;
    private PostType postType;
    private LocalDateTime postCreatedTimeStamp;


    @ManyToOne
    @JoinColumn(name = "fk_owner_user_id")
    User postOwner;

}
