package com.jsp.jboard.controller;

import com.jsp.jboard.request.PostCreateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    @GetMapping("")
    public String postLists() {
        return "list";
    }

    @GetMapping("/create-form")
    public String postCreateForm(Model model) {
        model.addAttribute("dto", new PostCreateDto());
        return "write";
    }
    @PostMapping("")
    public String writePost(@ModelAttribute("dto") PostCreateDto dto, BindingResult bs, Model model) {
        if (bs.hasErrors()) {
            log.error("[WRITE POST ERR]");
            return "list";
        }
        return "list";
    }
}
