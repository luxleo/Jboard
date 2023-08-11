package com.jsp.jboard.controller;

import com.jsp.jboard.domain.Comment;
import com.jsp.jboard.request.CommentCreateRequest;
import com.jsp.jboard.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * comments 컨트롤러의 경우 create, update,delete만 지원하면 된다
 * read의 경우 post와 함께 움직이기 때문이다.
 * ajax로 사용되는 api라 가정하고 만든다.
 */
@Slf4j
@Controller
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    // create comment

    /**
     * TODO: 1. restcontroller에서는 bindingresult를 사용하지 않는다는 모양인데 확실히 알기.
     * TODO: 2. bindingresult를 사용하지 않는 이유 : exception 처리를 해주기 때무니당.
     */
    @PostMapping("")
    public String createComment(@Validated @ModelAttribute(name = "commentDto") CommentCreateRequest commentDto, BindingResult bs, RedirectAttributes rda) {
        log.info("comment post accepted");
        if (bs.hasErrors()) {
            log.error("shit error! from comment controller");
            return "list";
        }
        log.info("value of dto = {}", commentDto);
        Comment comment = commentService.writeComment(commentDto);
        log.info("value of comment = {}", comment);
        rda.addAttribute("postId", commentDto.getPostId());
        log.info("comment post all good");
        return "redirect:/posts/{postId}";
    }

    @GetMapping("/{commentId}/delete")
    public String createComment(@PathVariable(name = "commentId") Long commentId,RedirectAttributes rda) {
        Long postId = commentService.deleteComment(commentId);
        rda.addAttribute("postId", postId);
        return "redirect:/posts/{postId}";

    }

    // update comment

    // delete comment

}
