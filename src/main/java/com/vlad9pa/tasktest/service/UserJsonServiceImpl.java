package com.vlad9pa.tasktest.service;

import com.vlad9pa.tasktest.entity.Contact;
import com.vlad9pa.tasktest.entity.Roles;
import com.vlad9pa.tasktest.entity.User;
import com.vlad9pa.tasktest.repository.ContactJsonRepository;
import com.vlad9pa.tasktest.repository.UserJsonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Profile("json")
@Service
public class UserJsonServiceImpl implements UserService{

    @Autowired
    private UserJsonRepository userJsonRepository;

    @Autowired
    private ContactJsonRepository contactJsonRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findByUsername(String username) {
        User user = userJsonRepository.findByUsername(username);
        if(user != null){
            user.setContacts(contactJsonRepository.getContactSet(user.getId()));
        }
        return user;
    }

    @Override
    public User findById(Long id) {
        User user = userJsonRepository.getOne(id);
        Set<Contact> contactSet = new HashSet<>();
        contactSet = contactJsonRepository.getContactSet(id);
        user.setContacts(contactSet);
        return user;
    }

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Roles> roles = new HashSet<Roles>();
        roles.add(Roles.ROLE_USER);
        user.setRoles(roles);
        Set<Contact> contacts = new HashSet<>();
        user.setContacts(contacts);
        userJsonRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userJsonRepository.delete(user);
        for(Contact contact: user.getContacts()){
            contactJsonRepository.delete(contact);
        }
    }

    @Override
    public void update(User user) {
        userJsonRepository.update(user);
    }

    @Override
    public void changePassword(User user, String password) {
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userJsonRepository.update(user);
    }

    @Override
    public void deleteAll() {
        userJsonRepository.deleteAll();
    }

    @Override
    public void addContactToPhoneBook(User user, Contact contact) {
        Set<Contact> contacts = user.getContacts();
        contacts.add(contact);
        user.setContacts(contacts);
        userJsonRepository.save(user);
    }
}
