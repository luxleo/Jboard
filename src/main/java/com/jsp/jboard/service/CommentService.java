package com.jsp.jboard.service;

import com.jsp.jboard.domain.Comment;
import com.jsp.jboard.domain.Post;
import com.jsp.jboard.domain.Users;
import com.jsp.jboard.repository.CommentRepository;
import com.jsp.jboard.repository.PostRepository;
import com.jsp.jboard.repository.UserRepository;
import com.jsp.jboard.request.CommentCreateRequest;
import com.jsp.jboard.response.post.comment.CommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Comment writeComment(CommentCreateRequest dto) {
        Post findPost = postRepository.findById(dto.getPostId());
        Users findUser = userRepository.findById(dto.getWriterName());

        Comment comment = Comment.builder()
                .writer(findUser)
                .parent(findPost)
                .content(dto.getContent())
                .build();
        return commentRepository.save(comment);
    }

    public List<CommentResponse> getCommentList(Long postId) {
        List<Comment> all = commentRepository.findAll(postId);
        return all.stream()
                .map(CommentResponse::new)
                .collect(Collectors.toList());
    }

    public Long deleteComment(Long commentId) {
        return commentRepository.delete(commentId);
    }
}
