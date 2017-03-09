package com.vlad9pa.tasktest.Service;

import com.vlad9pa.tasktest.Entity.Contact;
import com.vlad9pa.tasktest.Entity.User;
import com.vlad9pa.tasktest.Repository.ContactRepositry;
import com.vlad9pa.tasktest.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ContactServiceImpl implements ContactService{

    @Autowired
    private ContactRepositry contactRepositry;
    @Autowired
    private UserService userService;

    @Override
    public void save(User user,Contact contact) {
       userService.addContactToPhoneBook(user,contact);
    }

    @Override
    public void update(Contact contact) {
        contactRepositry.save(contact);
    }

    @Override
    public void delete(Contact contact) {
        contactRepositry.delete(contact);
    }

    @Transactional(readOnly = true)
    @Override
    public Contact findById(Long id) {
        Contact contact = contactRepositry.getOne(id);
        return contact;
    }
}
