package com.example.SystemAnalysisDesign.postKeyword.domain;

import com.example.SystemAnalysisDesign.keyword.domain.Keyword;
import com.example.SystemAnalysisDesign.post.domain.Post;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@Entity
@NoArgsConstructor
public class PostKeyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_keyword_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "keyword_id")
    private Keyword keyword;

    @Builder
    public PostKeyword(Long id, Post post, Keyword keyword) {
        this.id = id;
        this.post = post;
        this.keyword = keyword;
    }

    public void associateKeyword(Keyword keyword) {
        this.keyword = keyword;
        if (!keyword.getPostKeywords().contains(this)) {
            keyword.getPostKeywords().add(this);
        }
    }

    public void associatePost(Post post) {
        this.post = post;
        if (!post.getPostKeywords().contains(this)) {
            post.getPostKeywords().add(this);
        }
    }

}
