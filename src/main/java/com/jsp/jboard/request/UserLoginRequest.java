package com.jsp.jboard.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserLoginRequest {
    @NotEmpty(message = "아이디 입력은 필수")
    private String id;
    @NotEmpty(message = "비밀번호 입력은 필수")
    private String password;
}
