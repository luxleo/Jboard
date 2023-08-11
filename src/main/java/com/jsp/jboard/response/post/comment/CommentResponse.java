package com.jsp.jboard.response.post.comment;

import com.jsp.jboard.domain.Comment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class CommentResponse {
    private String content;
    private String writerName;
    private LocalDate createdAt;
    private Long id;

    public CommentResponse(Comment comment) {
        this.content = comment.getContent();
        this.writerName = comment.getWriter().getName();
        this.createdAt = comment.getCreated_at();
        this.id = comment.getId();
    }
}
