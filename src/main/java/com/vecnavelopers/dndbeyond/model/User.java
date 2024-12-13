package com.vecnavelopers.dndbeyond.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Data
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "auth0_id")
    private String auth0Id;

    private String username;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "profile_picture")
    private String profilePicture;

    @Column(name = "user_bio")
    private String userBio;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    private boolean enabled;

    public User() { this.enabled = true; }

    public User(String displayName, String profilePicture, String userBio, Timestamp createdAt, Timestamp updatedAt, boolean enabled) {
        this.displayName = displayName;
        this.profilePicture = profilePicture;
        this.userBio = userBio;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.enabled = true;
    }

    public User(String username) {
        this.username = username;
        this.enabled = true;
    }
//
//    public Long getId() { return id; }
//
//    public void setAuth0Id(String auth0Id) {
//        this.auth0Id = auth0Id;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }

}
