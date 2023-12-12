package com.vikas.instaBackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer followId;




    @ManyToOne
    @JoinColumn(name ="user" )
    User currentUserFollower;
    @ManyToOne
    @JoinColumn(name = "target")
    User currentUser;
}
