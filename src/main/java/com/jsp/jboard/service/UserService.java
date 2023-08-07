package com.jsp.jboard.service;

import com.jsp.jboard.domain.Users;
import com.jsp.jboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    /**
     * user 저장 성공시 id return
     */
    public String save(Users user) {
        return userRepository.save(user);
    }

    public Users findById(String id) {
        return userRepository.findById(id);
    }

    public Users doLogin(String id, String password) {
        Users findUser = userRepository.findById(id);
        if (!findUser.getPassword().equals(password)) {
            return null;
        }
        return findUser;
    }
}
