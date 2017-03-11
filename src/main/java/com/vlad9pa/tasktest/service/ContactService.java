package com.vlad9pa.tasktest.service;

import com.vlad9pa.tasktest.entity.Contact;
import com.vlad9pa.tasktest.entity.User;

import java.util.List;
import java.util.Set;
import java.util.SortedSet;

public interface ContactService {
    void save(User user, Contact contact);
    void update(Contact contact, Long id);
    void delete(Contact contact);
    void deleteAll();
    Contact findByFirstName(String firstName);
    Contact findById(Long id);
    List<Contact> getSortedList(Set<Contact> contacts);
    List<Contact> getSortedList(Set<Contact> contacts, String sortBy);
}
