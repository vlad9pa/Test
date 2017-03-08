package com.vlad9pa.tasktest.Service;

import com.vlad9pa.tasktest.Entity.Contact;
import com.vlad9pa.tasktest.Entity.Roles;
import com.vlad9pa.tasktest.Entity.User;
import com.vlad9pa.tasktest.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.HashSet;
import java.util.Set;

@Transactional
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Transactional(readOnly = true)
    @Override
    public User findUserById(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public void save(User user) {
        Set<Roles> roles = new HashSet<Roles>();
        roles.add(Roles.ROLE_USER);
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }

    @Override
    public void addToPhoneBook(User user, Contact contact) {
        Set<Contact> contacts = new HashSet<>();
        contacts.add(contact);
        user.setContacts(contacts);
        userRepository.save(user);
    }
}
