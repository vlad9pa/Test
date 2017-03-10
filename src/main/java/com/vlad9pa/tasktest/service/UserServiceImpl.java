package com.vlad9pa.tasktest.service;

import com.vlad9pa.tasktest.entity.Contact;
import com.vlad9pa.tasktest.entity.Roles;
import com.vlad9pa.tasktest.entity.User;
import com.vlad9pa.tasktest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.HashSet;
import java.util.Set;

@Transactional
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional(readOnly = true)
    @Override
    public User findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Transactional(readOnly = true)
    @Override
    public User findUserById(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Roles> roles = new HashSet<Roles>();
        roles.add(Roles.ROLE_USER);
        Set<Contact> contacts = new HashSet<>();
        user.setContacts(contacts);
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
    public void addContactToPhoneBook(User user, Contact contact) {
        Set<Contact> contacts;
        if(user.getContacts() != null){
            contacts = user.getContacts();
        }
        else{
            contacts = new HashSet<>();
        }
        contacts.add(contact);
        user.setContacts(contacts);
        userRepository.save(user);
    }
}
