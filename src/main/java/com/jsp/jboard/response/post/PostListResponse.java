package com.jsp.jboard.response.post;

import com.jsp.jboard.domain.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;

@Getter @Setter
public class PostListResponse {
    public Long id;
    public String title;
    public String writerNick;
    public String createdAt;
    public int hit;
    public PostListResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.writerNick = post.getWriter().getNickName();
        this.createdAt = post.getRDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.hit = post.getHit();
    }
}
