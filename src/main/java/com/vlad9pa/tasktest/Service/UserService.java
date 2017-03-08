package com.vlad9pa.tasktest.Service;

import com.vlad9pa.tasktest.Entity.User;

public interface UserService {
    User findUserByUsername(String username);
    void save(User user);
    void delete(User user);
    void update(User user);
}
