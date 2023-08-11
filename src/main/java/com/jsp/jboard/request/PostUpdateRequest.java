package com.jsp.jboard.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class PostUpdateRequest {
    private Long id;
    @NotEmpty(message = "잉 제목이 비었구만, 다시!")
    private String title;
    @NotEmpty(message = "잉 내용이 비었구만, 다시!")
    private String content;
    public PostUpdateRequest(Long id,String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
