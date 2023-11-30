package com.vikas.instaBackend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    @Column(nullable = false)
    @NotBlank
    private String commentBody;

    private LocalDateTime commentCreationTimeStamp;

    @ManyToOne
    @JoinColumn(name = "fk_post_id")
    Post instaPost;

    @ManyToOne
    @JoinColumn(name = "fk_commenter_id")
    User commenter;



}
