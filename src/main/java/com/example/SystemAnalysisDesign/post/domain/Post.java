    package com.example.SystemAnalysisDesign.post.domain;

    import com.example.SystemAnalysisDesign.post.domain.dto.PostCreateDto;
    import com.example.SystemAnalysisDesign.post.domain.dto.PostUpdateDto;
    import com.example.SystemAnalysisDesign.postKeyword.domain.PostKeyword;
    import com.example.SystemAnalysisDesign.user.domain.User;
    import com.example.SystemAnalysisDesign.userKeyword.domain.UserKeyword;
    import jakarta.persistence.*;
    import lombok.Builder;
    import lombok.Getter;
    import lombok.NoArgsConstructor;

    import java.time.LocalDateTime;
    import java.util.ArrayList;
    import java.util.List;


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

        @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<PostKeyword> postKeywords = new ArrayList<>();

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

        // 연관 관계 편의 메서드
        public void associateUser(User user) {
            this.user = user;
            if (!user.getPosts().contains(this)) {
                user.getPosts().add(this);
            }
        }

        public void addPostKeyword(PostKeyword postKeyword) {
            this.postKeywords.add(postKeyword);
            postKeyword.associatePost(this);
        }
    }
