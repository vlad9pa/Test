package com.vlad9pa.tasktest;

import com.vlad9pa.tasktest.entity.Contact;
import com.vlad9pa.tasktest.entity.User;
import com.vlad9pa.tasktest.service.ContactService;
import com.vlad9pa.tasktest.service.UserService;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertNotEquals;


/*
    In case that JpaTest must be complete good, I'll ignore test that check saves and deletes.
    And In case that Unit testing must be independent, in every method I create User and Contact object.
 */
@RunWith(SpringRunner.class)
@AutoConfigureDataJpa
@SpringBootTest
public class ServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private ContactService contactService;

    @After
    public void clearDB(){
        userService.deleteAll();
        contactService.deleteAll();
    }

    @Test
    public void shouldChangeContactInfo(){
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");
        user.setFullName("fullName");
        userService.save(user);
        Contact contact = new Contact();
        contact.setFirstName("firstName");
        contact.setLastName("lastName");
        contact.setMiddleName("secondName");
        contact.setEmail("email@email.com");
        contact.setMobileNumber("380982943055");
        contact.setHomePhoneNumber("4554547");
        contactService.save(user,contact);
        String oldName = contact.getFirstName();
        contact.setFirstName("newName");
        contactService.update(contact, contact.getId());
        assertNotEquals(oldName,contact.getFirstName());
    }

    // In case that it's must work good, sort ContactList  by default(first name) will be enough
    @Test
    public void shouldSortContactList(){
        Set<Contact> contactSet = new HashSet<>();
        Contact contact = new Contact();
        contact.setFirstName("CFirstContact");
        contact.setLastName("FirstContact");
        contact.setMiddleName("FirstContact");
        contact.setEmail("email@email.com");
        contact.setMobileNumber("380982943055");
        contact.setHomePhoneNumber("4554547");
        contactSet.add(contact);
        contact = new Contact();
        contact.setFirstName("ASecondContact");
        contact.setLastName("SecondContact");
        contact.setMiddleName("SecondContact");
        contact.setEmail("email@email.com");
        contact.setMobileNumber("380982943055");
        contact.setHomePhoneNumber("4554547");
        contactSet.add(contact);
        contact = new Contact();
        contact.setFirstName("AThirdContact");
        contact.setLastName("ThirdContact");
        contact.setMiddleName("ThirdContact");
        contact.setEmail("email@email.com");
        contact.setMobileNumber("380982943055");
        contact.setHomePhoneNumber("4554547");
        contactSet.add(contact);
        List<Contact>  contactList = new ArrayList<>(contactSet);
        List<Contact> sortedContactList = contactService.getSortedList(contactSet);
        assertNotEquals(contactList.get(0),sortedContactList.get(0));
    }

    @Test
    public void shouldChangeUserPassword(){
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");
        user.setFullName("fullName");
        userService.save(user);
        String newPassword = "newpassword";
        String oldPassword = user.getPassword();
        userService.changePassword(user,newPassword);
        assertNotEquals(oldPassword,user.getPassword());
    }

}
