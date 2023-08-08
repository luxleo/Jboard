package com.jsp.jboard.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "Article")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "no")
    private Long id;
    private String title;
    private String content;
    private int hit;
    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "writer")
    private Users writer;
    @Column(name = "regip")
    private String regIp;
    @Column(name = "rdate")
    private LocalDateTime rDate;

    @Builder
    public Post(String title, String content, Users writer, String regIp, LocalDateTime rDate) {
        this.title = title;
        this.content = content;
        this.hit = 0;
        this.writer = writer;
        this.regIp = regIp;
        this.rDate = rDate;
    }
}
