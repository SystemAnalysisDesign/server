package com.example.SystemAnalysisDesign.user.domain;

import com.example.SystemAnalysisDesign.post.domain.Post;
import com.example.SystemAnalysisDesign.user.domain.dto.UserCreateDto;
import com.example.SystemAnalysisDesign.userKeyword.domain.UserKeyword;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String email;
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<UserKeyword> userKeywords = new ArrayList<>();

    @Builder
    public User(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public static User from(UserCreateDto dto, BCryptPasswordEncoder encoder) {
        return User.builder()
                .email(dto.getEmail())
                .password(encoder.encode(dto.getPassword()))
                .build();
    }

    // 연관 관계 편의 메서드
    public void addUserKeyword(UserKeyword userKeyword) {
        this.userKeywords.add(userKeyword);
        userKeyword.associateUser(this);
    }

    public void addPost(Post post) {
        this.posts.add(post);
        post.associateUser(this);
    }
}
