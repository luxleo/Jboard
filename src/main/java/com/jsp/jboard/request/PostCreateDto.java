package com.jsp.jboard.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PostCreateDto {
    private Long id;
    private String title;
    private String content;
    private int hit;
}
