package com.otto.ProjectSpring.service;



import com.otto.ProjectSpring.entity.User;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

    User getUserByUsername(HttpServletRequest request);
    void addUser(User user);
    void deleteUser(String username);
    void updateUser(User user);
    boolean validate(User user, String password);
}
