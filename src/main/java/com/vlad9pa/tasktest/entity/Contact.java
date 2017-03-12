package com.vlad9pa.tasktest.entity;

import org.hibernate.validator.constraints.Email;
import org.springframework.context.annotation.Profile;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "contacts")
public class Contact{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "first_name")
    @NotNull(message = "This field is required.")
    @Size(min = 4, message = "First name must be over 4 characters.")
    @Pattern(regexp = "^(?=.{4,32}$)(?![_.])(?!.*[_.]{2})[a-zA-Z._]+(?<![_.])$",
            message = "Must contain only characters.")
    private String firstName;
    @Basic
    @Column(name = "last_name")
    @NotNull(message = "This field is required.")
    @Size(min = 4, message = "Last name must be over 4 characters.")
    @Pattern(regexp = "^(?=.{4,32}$)(?![_.])(?!.*[_.]{2})[a-zA-Z._]+(?<![_.])$",
            message = "Must contain only characters.")
    private String lastName;
    @Basic
    @Column(name = "middle_name")
    @NotNull(message = "This field is required.")
    @Size(min = 4, message = "Middle name must be over 4 characters.")
    @Pattern(regexp = "^(?=.{4,32}$)(?![_.])(?!.*[_.]{2})[a-zA-Z._]+(?<![_.])$",
            message = "Must contain only characters.")
    private String middleName;
    @Basic
    @Column(name = "email")
    @NotNull(message = "This field is required.")
    @Email
    private String email;
    @Basic
    @Column(name = "mobile_number")
    @NotNull(message = "This field is required.")
    @Pattern(regexp = "\\(?([3]{1})\\)?([8]{1}){1}?([0]{1}){1}?([0-9]{9})",
            message = "Mobile number must begin with 380 and size must be equal 12 symbols.")
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

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
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
        if (!middleName.equals(contact.middleName)) return false;
        if (!email.equals(contact.email)) return false;
        if (!mobileNumber.equals(contact.mobileNumber)) return false;
        return homePhoneNumber.equals(contact.homePhoneNumber);
    }
}
