package com.jsp.jboard.request;

import jakarta.validation.constraints.NotEmpty;

public class UserLogin {
    @NotEmpty(message = "아이디 입력은 필수")
    private String id;
    @NotEmpty(message = "비밀번호 입력은 필수")
    private String password;
}
