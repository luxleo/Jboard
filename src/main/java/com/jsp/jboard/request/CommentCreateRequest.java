package com.jsp.jboard.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommentCreateRequest {
    @NotEmpty
    private String writerName;
    @NotEmpty
    private String content;
    @NotNull
    private Long postId;
    @Builder
    public CommentCreateRequest(String writerName, String content, Long postId) {
        this.writerName = writerName;
        this.content = content;
        this.postId = postId;
    }
}
