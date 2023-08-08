package com.jsp.jboard.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PostCreateRequest {
    private String writer;
    private String title;
    private String content;
    @Builder
    public PostCreateRequest(String writer, String title, String content) {
        this.writer = writer;
        this.title = title;
        this.content = content;
    }
}
