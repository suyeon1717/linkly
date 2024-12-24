package com.example.linkly.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Entity
@Getter
@Table(name = "user")

public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 10)
    private String name;

    @Column(length = 30, nullable = false, unique = true)
    private String email;

    @Column(length = 255)
    private String password;

    // 프로필 사진
    @Column
    private String profileImg;

    // 프로필 소개
    @Column(length = 50)
    private String profileIntro;

    // 프로필 링크
    @Column
    private String profileUrl;

    public User() {
    }

    public User(String email, String password, String name,String profileImg, String profileIntro, String profileUrl) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.profileImg = profileImg;
        this.profileIntro = profileIntro;
        this.profileUrl = profileUrl;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void updatePassword(String password) {
        this.password = password;
    }

    public void updateProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public void updateProfileIntro(String profileIntro) {
        this.profileIntro = profileIntro;
    }

    public void updateProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }
}
