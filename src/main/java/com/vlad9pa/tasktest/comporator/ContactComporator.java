package com.vlad9pa.tasktest.comporator;

import com.vlad9pa.tasktest.entity.Contact;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class ContactComporator implements Comparator<Contact> {

    public enum Order{fName, lName, mNumber}

    private Order sortingBy;

    public void setSortingBy(Order sortingBy){
        this.sortingBy = sortingBy;
    }


    @Override
    public int compare(Contact contact1, Contact contact2) {
        switch(sortingBy) {
            case fName: return contact1.getFirstName().compareTo(contact2.getFirstName());
            case lName: return contact1.getLastName().compareTo(contact2.getLastName());
            case mNumber: return contact1.getMobileNumber().compareTo(contact2.getMobileNumber());
        }
        throw new RuntimeException("Practically unreachable code, can't be thrown");
    }
}
