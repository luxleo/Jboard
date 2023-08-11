package com.jsp.jboard.service;

import com.jsp.jboard.domain.Post;
import com.jsp.jboard.domain.Users;
import com.jsp.jboard.repository.PostRepository;
import com.jsp.jboard.repository.UserRepository;
import com.jsp.jboard.request.PostCreateRequest;
import com.jsp.jboard.request.PostUpdateRequest;
import com.jsp.jboard.response.post.PostDetailResponse;
import com.jsp.jboard.response.post.PostListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    public List<PostListResponse> getPostList() {
        Long postNum = postRepository.count();
        log.info("[POSTSERVICE] how many posts = {}", postNum);
        List<Post> all = postRepository.findAll();
        return all.stream()
                .map(PostListResponse::new)
                .collect(Collectors.toList());
    }

    public Long createPost(PostCreateRequest dto, String userIp) {
        Users findUser = userRepository.findById(dto.getWriter());
        Post post = Post.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(findUser)
                .rDate(LocalDateTime.now())
                .regIp(userIp).build();
        Long postId = postRepository.save(post);
        return postId;
    }

    public PostDetailResponse getPostDetailView(Long id) {
        Post foundPost = postRepository.findByIdWithWriter(id);
        return PostDetailResponse.builder()
                .id(id)
                .writer(foundPost.getWriter().getId())
                .title(foundPost.getTitle())
                .content(foundPost.getContent())
                .build();
    }
    public Long getPostNum() {
        return postRepository.count();
    }
    @Transactional
    public Long updatePost(PostUpdateRequest updateRequest) {
        Post foundPost = postRepository.findById(updateRequest.getId());
        foundPost.setTitle(updateRequest.getTitle());
        foundPost.setContent(updateRequest.getContent());
        return foundPost.getId();
    }

}
