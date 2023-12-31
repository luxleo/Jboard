package com.jsp.jboard.repository;

import com.jsp.jboard.domain.Users;

public interface UserRepository {
    public String save(Users user);

    public void delete(String id);

    public Users findById(String id);
}
