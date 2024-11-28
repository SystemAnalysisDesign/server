package com.example.SystemAnalysisDesign.postKeyword.domain;

import com.example.SystemAnalysisDesign.keyword.domain.Keyword;
import com.example.SystemAnalysisDesign.post.domain.Post;
import com.example.SystemAnalysisDesign.user.domain.User;
import jakarta.persistence.*;
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
}
