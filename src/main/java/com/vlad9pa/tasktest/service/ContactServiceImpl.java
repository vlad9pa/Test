package com.vlad9pa.tasktest.service;

import com.vlad9pa.tasktest.comporator.ContactComporator;
import com.vlad9pa.tasktest.entity.Contact;
import com.vlad9pa.tasktest.entity.User;
import com.vlad9pa.tasktest.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional
@Service
public class ContactServiceImpl implements ContactService{

    @Autowired
    private ContactComporator contactComporator;

    @Autowired
    private ContactRepository contactRepository;

    // Save method take user entity for knowing to which user entity must be linked contact entity
    @Override
    public void save(User user,Contact contact) {
       contact.setUser(user);
       contactRepository.save(contact);
    }

    // In case that when editing when editing contact in web contact entity create new Contact-object,
    // update method take id by which it's find contact entity that must be edited.
    @Override
    public void update(Contact newContact, Long id) {
        Contact oldContact = contactRepository.findOne(id);
        oldContact.setFirstName(newContact.getFirstName());
        oldContact.setLastName(newContact.getLastName());
        oldContact.setMiddleName(newContact.getMiddleName());
        oldContact.setEmail(newContact.getEmail());
        oldContact.setMobileNumber(newContact.getMobileNumber());
        oldContact.setHomePhoneNumber(newContact.getHomePhoneNumber());
        contactRepository.save(oldContact);
    }

    @Override
    public void delete(Contact contact) {
        contactRepository.delete(contact);
    }

    @Override
    public void deleteAll() {
        contactRepository.deleteAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Contact findById(Long id) {
        Contact contact = contactRepository.findOne(id);
        return contact;
    }

    @Transactional(readOnly = true)
    @Override
    public Contact findByFirstName(String firstName) {
        Contact contact = contactRepository.findByFirstName(firstName);
        return contact;
    }

    @Override
    public List<Contact> getSortedList(Set<Contact> contacts) {
        contactComporator.setSortingBy(ContactComporator.Order.fName);
        List<Contact> contactList = new ArrayList<>(contacts);
        Collections.sort(contactList, contactComporator);
        return contactList;
    }

    @Override
    public List<Contact> getSortedList(Set<Contact> contacts, String sortBy) {
        switch (sortBy){
            case "Name":contactComporator.setSortingBy(ContactComporator.Order.fName);break;
            case "Last Name":contactComporator.setSortingBy(ContactComporator.Order.lName);break;
            case "Mobile Number":contactComporator.setSortingBy(ContactComporator.Order.mNumber);break;
            default:contactComporator.setSortingBy(ContactComporator.Order.fName); break;
        }
        List<Contact> contactList = new ArrayList<>(contacts);
        Collections.sort(contactList, contactComporator);
        return contactList;
    }
}
