package com.example.SystemAnalysisDesign.keyword.domain;

import com.example.SystemAnalysisDesign.keyword.domain.dto.KeywordCreateDto;
import com.example.SystemAnalysisDesign.keyword.domain.dto.KeywordUpdateDto;
import com.example.SystemAnalysisDesign.postKeyword.domain.PostKeyword;
import com.example.SystemAnalysisDesign.userKeyword.domain.UserKeyword;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "keyword_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "keyword", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserKeyword> userKeywords = new ArrayList<>();

    @OneToMany(mappedBy = "keyword", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostKeyword> postKeywords = new ArrayList<>();

    @Builder
    public Keyword(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Keyword from(KeywordCreateDto dto) {
        return Keyword.builder()
                .name(dto.getName())
                .build();
    }

    public Keyword update(KeywordUpdateDto dto) {
        this.name = dto.getName();
        return this;
    }
}
