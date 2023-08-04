package com.jsp.jboard.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter //Todo: entity class 에도 setter를 쓰는지?
@Entity
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Users {
    @Id
    @Column(name = "UID", length = 20)
    private String id;
    private String email;
    private String password;
    @Column(length = 20)
    private String name;
    @Column(length = 20, unique = true, name = "nick")
    private String nickName;
    @Column(length = 13, unique = true)
    private String hp;
    @Enumerated(value = EnumType.STRING) // Todo: default value설정하는 방법 연구하기
    private UserRole role;

    @Column(length = 5)
    private String zip;
    private String addr1;
    private String addr2;
    @Column(name = "reg_ip", length = 100)
    private String regIp;
    private LocalDateTime regDate;
    private String leaveDate;

    @Builder
    public Users(String id, String email, String password, String name, String nickName, String hp, UserRole role, String zip, String addr1, String addr2, String regIp, LocalDateTime regDate, String leaveDate) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickName = nickName;
        this.hp = hp;
        this.role = role;
        this.zip = zip;
        this.addr1 = addr1;
        this.addr2 = addr2;
        this.regIp = regIp;
        this.regDate = regDate;
        this.leaveDate = leaveDate;
    }
    //    @PrePersist
//    public void setDefaultVal() {
//        if (role == null) {
//            role = UserRole.USER;
//        }
//    }

}
