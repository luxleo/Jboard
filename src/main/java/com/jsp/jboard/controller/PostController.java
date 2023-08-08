package com.jsp.jboard.controller;

import com.jsp.jboard.config.PostPagingConfig;
import com.jsp.jboard.domain.Users;
import com.jsp.jboard.request.PostCreateRequest;
import com.jsp.jboard.request.PostSearchCondRequest;
import com.jsp.jboard.response.post.PostDetailResponse;
import com.jsp.jboard.response.post.PostListResponse;
import com.jsp.jboard.service.PostService;
import com.jsp.jboard.service.dto.PostSearchCond;
import com.jsp.jboard.service.query.PostListService;
import com.jsp.jboard.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final PostListService postListService;

    //@GetMapping("")
    public String postLists(Model model) {
        List<PostListResponse> posts = postService.getPostList();
        model.addAttribute("posts", posts);
        return "list";
    }
    @GetMapping("")
    public String postListsWithpage(PostSearchCondRequest request, Model model) {
        Long postNum = postService.getPostNum();

        List<PostListResponse> posts = postListService.getPostListWithPage(new PostSearchCond(request,postNum));

        PagingNavBarMaker paging = new PagingNavBarMaker(request,postNum );
        model.addAttribute("posts", posts);
        model.addAttribute("paging", paging);
        return "list";
    }
    //TODO: argumentResolver로 간단히 처리하기
    @GetMapping("/create-form")
    public String postCreateForm(Model model,HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Users user = (Users) session.getAttribute(SessionConst.LOGIN_MEMBER);
        log.info("[LOGINCONTROLLER] login user = {}", user.getId());
        PostCreateRequest dto = PostCreateRequest.builder()
                .writer(user.getId())
                .build();
        model.addAttribute("dto", dto);
        return "write";
    }

    @PostMapping("")
    public String writePost(@ModelAttribute("dto") PostCreateRequest dto, BindingResult bs, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        if (bs.hasErrors()) {
            log.error("[WRITE POST ERR]");
            return "write";
        }
        log.info("[POST CREATE] got information user id= {}", dto.getWriter());
        Long postId = postService.createPost(dto, request.getRemoteAddr());
        redirectAttributes.addAttribute("postId", postId);
        return "redirect:/posts/{postId}";
    }

    @GetMapping("/{postId}")
    public String postDetail(@PathVariable Long postId, Model model) {
        PostDetailResponse post = postService.getPostDetailView(postId);
        model.addAttribute("post", post);
        return "view";
    }

    @Getter @Setter
    static class PagingNavBarMaker{
        private int start = 0;
        private int currentPage = 1;
        private Long total = 0L;
        private int lastPageNum = 0;
        private int pageGroupCurrent = 1;
        private int pageGroupStart = 1;
        private int pageGroupEnd = 0;
        private int pageStartNum = 0;

        public PagingNavBarMaker(PostSearchCondRequest request,Long postNum) {
            if (request.getRequestPage() != null) {
                currentPage = request.getRequestPage();
            }
            start = (currentPage - 1) * PostPagingConfig.PAGESIZE;
            total = postNum;
            if (total % 10 == 0) {
                lastPageNum = (int) (total / 10);
            }else{
                lastPageNum = (int) ((total / 10)+1);
            }

            pageGroupCurrent = (int) Math.ceil(currentPage / 10.0);
            pageGroupStart = (pageGroupCurrent - 1) * 10 + 1;
            pageGroupEnd = Math.min(pageGroupCurrent * 10, lastPageNum);
            pageStartNum = (int) (total - start);
        }
    }
}
