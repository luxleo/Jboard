package com.jsp.jboard.service.dto;

import com.jsp.jboard.config.PostPagingConfig;
import com.jsp.jboard.request.PostSearchCondRequest;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PostSearchCond {
    private int requestPage;

    public PostSearchCond(PostSearchCondRequest request, Long postNum) {
        if (request.getRequestPage() == null || request.getRequestPage()<0 || postNum<request.getRequestPage()* PostPagingConfig.PAGESIZE) {
            requestPage =1;
        }else {
            requestPage = request.getRequestPage();
        }
    }
}
