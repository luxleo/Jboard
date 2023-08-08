package com.jsp.jboard.request;

import com.jsp.jboard.domain.UserRole;
import com.jsp.jboard.domain.Users;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreateRequest {
    @NotBlank(message = "잉? 아이디가 비었는뎁, 다시!")
    private String id;
    @Email
    private String email;
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[^A-Za-z\\d\\s]).{5,15}$", message = "공백 없이 특수문자 영어, 숫자 조합으로 4-15자로 작성하도록")
    private String password;
    @Pattern(regexp = "^[a-zA-Z]*$",message = "누가 영어이름을 안쓰는가?, 숫자도 포함하지 말라")
    private String name;
    private String nickName;
    private String hp;

    private String zip;
    private String addr1;
    private String addr2;
    @Builder.Default
    private UserRole role = UserRole.USER;

    // 밑의 세 필드는 persist하기 전에 처리한다.
    //private String regIp;
    //private LocalDateTime regDate;
    //private LocalDateTime leaveDate;
    public Users toUser() {
        return Users.builder()
                .id(id)
                .email(email)
                .password(password)
                .name(name)
                .nickName(nickName)
                .hp(hp)
                .zip(zip)
                .addr1(addr1)
                .addr2(addr2)
                .role(role)
                .regDate(LocalDateTime.now())
                .build();
    }

}
