package com.jsp.jboard.response.post;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PostDetailResponse {

    private Long id;
    private String writer;
    private String title;
    private String content;
    @Builder
    public PostDetailResponse(Long id,String writer,String title, String content) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
    }
}
