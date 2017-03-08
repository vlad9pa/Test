package com.vlad9pa.tasktest.Entity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class PhoneBook{
    private Long id;
    private Long userInfoId;
    private String firstName;
    private String lastName;
    private String secondName;
    private String email;
    private String mobileNumber;
    private String homePhoneNumber;
    @OneToMany
    private Set<Contact> contactSet;
}
