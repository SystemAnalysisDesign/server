package com.example.SystemAnalysisDesign.userKeyword.domain;

import com.example.SystemAnalysisDesign.keyword.domain.Keyword;
import com.example.SystemAnalysisDesign.user.domain.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@Entity
@NoArgsConstructor
public class UserKeyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_keyword_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "keyword_id")
    private Keyword keyword;

    @Builder
    public UserKeyword(Long id, User user, Keyword keyword) {
        this.id = id;
        this.user = user;
        this.keyword = keyword;
    }

    // 연관 관계 편의 메서드
    public void associateUser(User user) {
        this.user = user;
        if (!user.getUserKeywords().contains(this)) {
            user.getUserKeywords().add(this);
        }
    }

    public void associateKeyword(Keyword keyword) {
        this.keyword = keyword;
        if (!keyword.getUserKeywords().contains(this)) {
            keyword.getUserKeywords().add(this);
        }
    }

}
