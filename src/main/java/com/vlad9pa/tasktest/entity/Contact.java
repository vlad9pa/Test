package com.vlad9pa.tasktest.entity;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "contacts")
public class Contact{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "first_name")
    @OrderColumn(name = "first_name")
    @NotNull
    @Size(min = 4, message = "Minimum 4 symbols")
    private String firstName;
    @Basic
    @Column(name = "last_name")
    @NotNull
    @Size(min = 4, message = "Minimum 4 symbols")
    private String lastName;
    @Basic
    @Column(name = "second_name")
    @NotNull
    @Size(min = 4, message = "Minimum 4 symbols")
    private String secondName;
    @Basic
    @Column(name = "email")
    @Email
    private String email;
    @Basic
    @Column(name = "mobile_number")
    @NotNull
    @Size(max = 13, min = 12, message = "Wrong Mobile Number Format")
    @Pattern(regexp = "\\(?([3]{1})\\)?([8]{1}){1}?([0]{1}){1}?([0-9]{9})")
    private String mobileNumber;
    @Basic
    @Column(name = "home_phone_number")
    private String homePhoneNumber;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getHomePhoneNumber() {
        return homePhoneNumber;
    }

    public void setHomePhoneNumber(String homePhoneNumber) {
        this.homePhoneNumber = homePhoneNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (!firstName.equals(contact.firstName)) return false;
        if (!lastName.equals(contact.lastName)) return false;
        if (!secondName.equals(contact.secondName)) return false;
        if (!email.equals(contact.email)) return false;
        if (!mobileNumber.equals(contact.mobileNumber)) return false;
        return homePhoneNumber.equals(contact.homePhoneNumber);
    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + secondName.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + mobileNumber.hashCode();
        result = 31 * result + homePhoneNumber.hashCode();
        return result;
    }
}
