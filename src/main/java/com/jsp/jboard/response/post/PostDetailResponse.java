package com.jsp.jboard.response.post;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PostDetailResponse {
    public String title;
    public String content;
    @Builder
    public PostDetailResponse(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
