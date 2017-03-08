package com.vlad9pa.tasktest.Entity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Contact {
    private Long id;
    private String firstName;
    private String lastName;
    private String secondName;
    private String email;
    private String mobileNumber;
    private String homePhoneNumber;
    @OneToMany(mappedBy = "contactSet")
    private PhoneBook phoneBook;
}
