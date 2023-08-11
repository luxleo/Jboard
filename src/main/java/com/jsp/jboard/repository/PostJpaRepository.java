package com.jsp.jboard.repository;

import com.jsp.jboard.domain.Post;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class PostJpaRepository implements PostRepository {
    private final EntityManager em;
    @Override
    public Long save(Post post) {
        em.persist(post);
        return post.getId();
    }

    @Override
    public List<Post> findAll() {
        return em.createQuery(
                "select p from Post p "+
                        "join fetch p.writer w",Post.class)
                .getResultList();
    }
    @Override
    public Post findById(Long id) {
        return em.find(Post.class, id);
    }

    @Override
    public Long count() {
        Long postNum = em.createQuery(
                "select COUNT(p) from Post p", Long.class
        ).getSingleResult();
        return postNum;
    }
    @Override
    public Post findByIdWithWriter(Long pid) {
        return em.createQuery(
                        "select p from Post p " +
                                "join fetch p.writer " +
                                "where p.id = :postId", Post.class
                ).setParameter("postId", pid)
                .getSingleResult();

    }
}
