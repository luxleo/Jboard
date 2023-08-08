package com.jsp.jboard.service.query;

import com.jsp.jboard.repository.query.PostListRepository;
import com.jsp.jboard.response.post.PostListResponse;
import com.jsp.jboard.service.dto.PostSearchCond;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostListService {
    private final PostListRepository postListRepository;
    public List<PostListResponse> getPostListWithPage(PostSearchCond searchCond) {
        List<PostListResponse> result = postListRepository.getPostsWithPage(searchCond);
        return result;
    }
}
