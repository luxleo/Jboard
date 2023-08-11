package com.jsp.jboard.repository;

import com.jsp.jboard.domain.Comment;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
@RequiredArgsConstructor
public class CommentRepository {
    private final EntityManager em;

    public Comment save(Comment comment) {
        em.persist(comment);
        return comment;
    }

    public List<Comment> findAll(Long postId) {
        return em.createQuery(
                        "select c from Comment c " +
                                "join fetch c.writer w " +
                                "join fetch c.parent p " +
                                "where p.id = :postId",Comment.class
                ).setParameter("postId", postId)
                .getResultList();
    }

    public Long delete(Long commentId) {
        Comment comment = em.createQuery(
                "select c from Comment c " +
                        "join fetch c.parent p ", Comment.class
        ).getSingleResult();
        em.remove(comment);
        return comment.getParent().getId();
    }
}
