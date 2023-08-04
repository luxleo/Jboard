package com.jsp.jboard.controller;

import com.jsp.jboard.domain.Users;
import com.jsp.jboard.request.UserCreate;
import com.jsp.jboard.request.UserLogin;
import com.jsp.jboard.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("dto", new UserCreate());
        return "user/register";
    }

    @PostMapping("/register")
    public String createUser(@Validated @ModelAttribute("dto") UserCreate dto, BindingResult bs,
                             RedirectAttributes redirectAttributes) {
        if (bs.hasErrors()) {
            log.info("attr set to binding res!");
            return "user/register";
        }
        log.info("value of dto = {}", dto);
        log.info("ispassword null = {}", dto.getPassword() == null);
        Users createdUser = dto.toUser();
        userService.save(createdUser);
        log.info("created user info ={}", createdUser);

        redirectAttributes.addAttribute("userId", createdUser.getId());
        return "redirect:/user/users/{userId}";
    }

    @GetMapping("/users/{userId}")
    public String getUser(@PathVariable String userId, Model model) {
        log.info("[CONTROLLER]getUser method called");
        Users findUser = userService.findById(userId);
        log.info("[FINDUSER] user name : {}", findUser.getName());
        model.addAttribute("user", findUser);
        return "user/userDetail";
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("dto", new UserLogin());
        return "user/login";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute("dto") UserLogin dto, BindingResult bs,
                        RedirectAttributes redirectAttributes) {
        if (bs.hasErrors()) {
            log.info("[LOGINERR] 오이오이, 그런 유저는 없다구");
            return "user/login";
        }
    }
}
