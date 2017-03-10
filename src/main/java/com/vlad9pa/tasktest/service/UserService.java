package com.vlad9pa.tasktest.service;

import com.vlad9pa.tasktest.entity.Contact;
import com.vlad9pa.tasktest.entity.User;

public interface UserService {
    User findByUsername(String username);
    User findUserById(Long id);
    void save(User user);
    void delete(User user);
    void update(User user);
    void addContactToPhoneBook(User user,Contact contact);
}
