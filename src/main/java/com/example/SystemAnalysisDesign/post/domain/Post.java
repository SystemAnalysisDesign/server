    package com.example.SystemAnalysisDesign.post.domain;

    import com.example.SystemAnalysisDesign.post.domain.dto.PostCreateDto;
    import com.example.SystemAnalysisDesign.post.domain.dto.PostUpdateDto;
    import com.example.SystemAnalysisDesign.user.domain.User;
    import jakarta.persistence.*;
    import lombok.Builder;
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

        @Builder
        public Post(Long id, User user, String title, String content, LocalDateTime createdAt) {
            this.id = id;
            this.user = user;
            this.title = title;
            this.content = content;
            this.createdAt = createdAt;
        }

        public static Post from(PostCreateDto dto, User user) {
            return Post.builder()
                    .user(user)
                    .title(dto.getTitle())
                    .content(dto.getContent())
                    .createdAt(LocalDateTime.now())
                    .build();
        }

        public Post update(PostUpdateDto dto) {
            this.title = dto.getTitle();
            this.content = dto.getContent();
            return this;
        }
    }
