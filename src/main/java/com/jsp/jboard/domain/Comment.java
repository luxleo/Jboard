package com.jsp.jboard.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String content;
    private LocalDate created_at;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "no")
    private Post parent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UID")
    private Users writer;

    @Builder
    public Comment(String content, Post parent, Users writer) {
        this.content = content;
        this.parent = parent;
        this.writer = writer;
        this.created_at = LocalDate.now();
    }
}
