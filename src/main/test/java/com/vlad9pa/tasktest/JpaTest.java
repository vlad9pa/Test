package com.vlad9pa.tasktest;

import com.vlad9pa.tasktest.entity.Contact;
import com.vlad9pa.tasktest.entity.User;
import com.vlad9pa.tasktest.repository.ContactRepository;
import com.vlad9pa.tasktest.repository.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@AutoConfigureDataJpa
@SpringBootTest
public class JpaTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactRepository contactRepository;

    private User user;
    private Contact contact;

    @After
    public void clearDB(){
        userRepository.deleteAll();
        contactRepository.deleteAll();
    }

    @Before
    public void repositoriesNotEmpty(){
        assertThat(userRepository).isNotNull();
        assertThat(contactRepository).isNotNull();
    }

    @Test
    public void shouldSaveUser(){
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");
        user.setFullName("fullName");
        userRepository.save(user);
        assertThat(userRepository.findByUsername(user.getUsername())).isNotNull();
        assertTrue(user.equals(userRepository.findByUsername(user.getUsername())));
    }

    @Test
    public void shouldDeleteUser(){
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");
        user.setFullName("fullName");
        userRepository.save(user);
        userRepository.delete(user);
        assertThat(userRepository.findByUsername(user.getUsername())).isNull();
    }


    @Test
    public void shouldSaveContact(){
        Contact contact = new Contact();
        contact.setFirstName("firstName");
        contact.setLastName("lastName");
        contact.setMiddleName("secondName");
        contact.setEmail("email@email.com");
        contact.setMobileNumber("380982943055");
        contact.setHomePhoneNumber("4554547");
        contactRepository.save(contact);
        assertThat(contactRepository.findByFirstName(contact.getFirstName())).isNotNull();
        assertTrue(contact.equals(contactRepository.findByFirstName(contact.getFirstName())));
    }

    @Test
    public void shouldSaveContactThroughSavingUser(){
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");
        user.setFullName("fullName");
        Set<Contact> contactSet = new HashSet<>();
        Contact contact = new Contact();
        contact.setFirstName("firstName");
        contact.setLastName("lastName");
        contact.setMiddleName("secondName");
        contact.setEmail("email@email.com");
        contact.setMobileNumber("380982943055");
        contact.setHomePhoneNumber("4554547");
        contactSet.add(contact);
        user.setContacts(contactSet);
        userRepository.save(user);
        assertThat(contactRepository.findByFirstName(contact.getFirstName())).isNotNull();
        assertTrue(contact.equals(contactRepository.findByFirstName(contact.getFirstName())));
    }

    @Test
    public void shouldDeleteContact(){
        Contact contact = new Contact();
        contact.setFirstName("firstName");
        contact.setLastName("lastName");
        contact.setMiddleName("secondName");
        contact.setEmail("email@email.com");
        contact.setMobileNumber("380982943055");
        contact.setHomePhoneNumber("4554547");
        contactRepository.save(contact);
        contactRepository.delete(contact);
        assertThat(contactRepository.findByFirstName(contact.getFirstName())).isNull();
    }

}
