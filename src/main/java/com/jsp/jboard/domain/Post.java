package com.jsp.jboard.domain;

import com.jsp.jboard.request.PostCreateDto;
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
    @ManyToOne(cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "id")
    private Users writer;
    @Column(name = "regip")
    private String regIp;
    @Column(name = "rdate")
    private LocalDateTime rDate;

    @Builder
    public Post(PostCreateDto dto) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.hit = hit;
        this.writer = writer;
        this.regIp = regIp;
        this.rDate = rDate;
    }
}
