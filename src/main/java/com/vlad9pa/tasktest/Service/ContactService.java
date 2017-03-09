package com.vlad9pa.tasktest.Service;

import com.vlad9pa.tasktest.Entity.Contact;
import com.vlad9pa.tasktest.Entity.User;

public interface ContactService {
    void save(User user, Contact contact);
    void update(Contact contact);
    void delete(Contact contact);
    Contact findById(Long id);
}
