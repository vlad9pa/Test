package com.vlad9pa.tasktest.Service;

import com.vlad9pa.tasktest.Entity.Contact;
import com.vlad9pa.tasktest.Entity.User;

public interface UserService {
    User findByUsername(String username);
    User findUserById(Long id);
    void save(User user);
    void delete(User user);
    void update(User user);
    void addContactToPhoneBook(User user,Contact contact);
}
