package com.jsp.jboard.repository;

import com.jsp.jboard.domain.Users;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Repository
@Transactional
@RequiredArgsConstructor
public class UserJpaRepository implements UserRepository{
    private final EntityManager em;
    @Override
    public String save(Users user) {
        em.persist(user);
        return user.getId();
    }
    @Override
    public void delete(String id) {
        log.info("user delete");
    }

    @Override
    public Users findById(String id) {
        Users users = em.find(Users.class, id);
        return users;
    }
}
