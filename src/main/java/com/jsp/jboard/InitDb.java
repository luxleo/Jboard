package com.jsp.jboard;

import com.jsp.jboard.domain.Post;
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
        initService.createUserAndPosts(5);
        log.info("[INIT SERVICE] finish");
    }
    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;

        public void createUserAndPosts(int postNum) {
            Users user = Users.builder()
                    .id("legends")
                    .password("1234")
                    .name("빈지노")
                    .nickName("goozino")
                    .role(UserRole.USER)
                    .regDate(LocalDateTime.now())
                    .hp("010-1234-1234")
                    .email("ex1@gmail.com")
                    .build();
            Users user2 = Users.builder()
                    .id("dragon")
                    .password("1234")
                    .name("이센스")
                    .nickName("E_SENSE")
                    .role(UserRole.USER)
                    .regDate(LocalDateTime.now())
                    .hp("010-1234-5678")
                    .email("ex2@gmail.com")
                    .build();
            try {
                em.persist(user);
                em.persist(user2);

                createPost(postNum,user);
                createPost(postNum, user2);
            }catch (Exception e){
                log.error("[BAD BAD]");
            }
        }
        public void createPost(int num,Users writer){
            for (int i = 0; i < num; i++) {
                Post post = Post.builder()
                        .title(String.valueOf(i))
                        .content(String.valueOf(i))
                        .writer(writer)
                        .rDate(LocalDateTime.now())
                        .regIp(null)
                        .build();
                em.persist(post);
            }
        }
    }
}
