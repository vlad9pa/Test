package com.vlad9pa.tasktest.service;

import com.vlad9pa.tasktest.comporator.ContactComporator;
import com.vlad9pa.tasktest.entity.Contact;
import com.vlad9pa.tasktest.entity.User;
import com.vlad9pa.tasktest.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    private List<Contact> contactList;

    @Override
    public void save(User user,Contact contact) {
       contact.setUser(user);
       contactRepository.save(contact);
    }

    @Override
    public void update(Contact newContact, Long id) {
        Contact oldContact = contactRepository.findOne(id);
        oldContact.setFirstName(newContact.getFirstName());
        oldContact.setLastName(newContact.getLastName());
        oldContact.setSecondName(newContact.getSecondName());
        oldContact.setEmail(newContact.getEmail());
        oldContact.setMobileNumber(newContact.getMobileNumber());
        oldContact.setHomePhoneNumber(newContact.getHomePhoneNumber());
        contactRepository.save(oldContact);
    }

    @Override
    public void delete(Contact contact) {
        contactRepository.delete(contact);
    }

    @Transactional(readOnly = true)
    @Override
    public Contact findById(Long id) {
        Contact contact = contactRepository.getOne(id);
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
            case "fName":contactComporator.setSortingBy(ContactComporator.Order.fName);break;
            case "lName":contactComporator.setSortingBy(ContactComporator.Order.lName);break;
            case "mNumber":contactComporator.setSortingBy(ContactComporator.Order.mNumber);break;
            default:contactComporator.setSortingBy(ContactComporator.Order.mNumber); break;
        }

        List<Contact> contactList = new ArrayList<>(contacts);
        Collections.sort(contactList, contactComporator);
        return contactList;
    }
}
