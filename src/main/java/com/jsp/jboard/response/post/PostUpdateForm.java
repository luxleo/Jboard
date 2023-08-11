package com.jsp.jboard.response.post;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PostUpdateForm {
    private Long id;
    private String title;
    private String content;
    @Builder
    public PostUpdateForm(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
