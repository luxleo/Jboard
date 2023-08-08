package com.jsp.jboard.repository;

import com.jsp.jboard.domain.Post;

import java.util.List;

public interface PostRepository {
    public Long save(Post post);
    public List<Post> findAll();

    public Post findById(Long id);

    public Long count();
}
