package com.example.SystemAnalysisDesign.notification.domain;

import com.example.SystemAnalysisDesign.post.domain.Post;
import com.example.SystemAnalysisDesign.user.domain.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@Entity
@NoArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    private String content;
    private LocalDateTime created_at;

    @Builder
    public Notification(User user, Post post, String content, LocalDateTime created_at) {
        this.user = user;
        this.post = post;
        this.content = content;
        this.created_at = LocalDateTime.now();
    }
}
