package com.jsp.jboard.repository.query;

import com.jsp.jboard.domain.Post;
import com.jsp.jboard.response.post.PostListResponse;
import com.jsp.jboard.service.dto.PostSearchCond;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.jsp.jboard.config.PostPagingConfig.PAGESIZE;

@Slf4j
@Repository
@Transactional
@RequiredArgsConstructor
public class PostListRepository {
    private final EntityManager em;
    public List<PostListResponse> getPostsWithPage(PostSearchCond searchCond) {
        List<Post> all = em.createQuery(
                        "select p from Post p " +
                                "join fetch p.writer w ", Post.class
                ).setFirstResult((searchCond.getRequestPage()-1) * PAGESIZE)
                .setMaxResults(PAGESIZE)
                .getResultList();
        return all.stream()
                .map(PostListResponse::new)
                .collect(Collectors.toList());
    }
}
