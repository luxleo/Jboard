package com.jsp.jboard;

import com.jsp.jboard.domain.UserRole;
import com.jsp.jboard.domain.Users;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class InitDb {
    private final InitService initService;

    @PostConstruct
    void init() {
        log.info("[INIT SERVICE] start");
        initService.createUser();
        log.info("[INIT SERVICE] finish");
    }
    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;

        public void createUser() {
            Users user = Users.builder()
                    .id("legends")
                    .password("1234aA!")
                    .name("빈지노")
                    .nickName("goozino")
                    .role(UserRole.USER)
                    .regDate(LocalDateTime.now())
                    .hp("010-1234-1234")
                    .email("ex1@gmail.com")
                    .build();
            try {
                em.persist(user);
                String id = user.getId();
                //em.flush();

                Users findUser = em.find(Users.class, id);
                log.info("[save WELL?] user name = {}", findUser.getName());
                log.info("ok good");
            }catch (Exception e){
                log.error("[BAD BAD]");
            }
        }
    }
}
