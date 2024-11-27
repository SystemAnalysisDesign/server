    package com.example.SystemAnalysisDesign.post.domain;

    import com.example.SystemAnalysisDesign.user.domain.User;
    import jakarta.persistence.*;
    import lombok.Getter;
    import lombok.NoArgsConstructor;

    import java.time.LocalDateTime;


    @Getter
    @Entity
    @NoArgsConstructor
    public class Post {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "post_id")
        private Long id;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id")
        private User user;

        private String title;
        private String content;
        private LocalDateTime createdAt;
    }
