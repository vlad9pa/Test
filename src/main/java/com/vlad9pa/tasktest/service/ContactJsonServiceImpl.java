package com.vlad9pa.tasktest.service;

import com.vlad9pa.tasktest.comporator.ContactComporator;
import com.vlad9pa.tasktest.entity.Contact;
import com.vlad9pa.tasktest.entity.User;
import com.vlad9pa.tasktest.repository.ContactJsonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Profile("json")
@Service
public class ContactJsonServiceImpl implements ContactService {

    @Autowired
    private ContactComporator contactComporator;

    @Autowired
    private ContactJsonRepository contactJsonRepository;

    @Override
    public void save(User user, Contact contact) {
        contact.setUser(user);
        contactJsonRepository.save(contact);
    }

    @Override
    public void update(Contact contact, Long id) {
        Contact oldContact = contactJsonRepository.getOne(id);
        contact.setId(id);
        contact.setUserID(oldContact.getUserID());
        contactJsonRepository.update(contact);
    }

    @Override
    public void delete(Contact contact) {
        contactJsonRepository.delete(contact);
    }

    @Override
    public void deleteAll() {
        contactJsonRepository.deleteAll();
    }

    @Override
    public Contact findById(Long id) {
        return contactJsonRepository.getOne(id);
    }

    @Override
    public List<Contact> getSortedList(Set<Contact> contacts) {
        contactComporator.setSortingBy(ContactComporator.Order.fName);
        List<Contact> contactList;
        if(contacts != null){
            contactList = new ArrayList<>(contacts);
            Collections.sort(contactList, contactComporator);
        }
        else {
            contactList = new ArrayList<>();
        }
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
        List<Contact> contactList;
        if(contacts != null){
            contactList = new ArrayList<>(contacts);
            Collections.sort(contactList, contactComporator);
        }
        else {
            contactList = new ArrayList<>();
        }
        return contactList;
    }
}
