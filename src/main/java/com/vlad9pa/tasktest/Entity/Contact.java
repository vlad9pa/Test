package com.vlad9pa.tasktest.Entity;

import org.hibernate.validator.constraints.Email;
import org.junit.validator.ValidateWith;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "contacts")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "first_name")
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
    @Pattern(regexp = "\\(?([3,8,0]{3})\\)?([0-9]{9})")
    private String mobileNumber;
    @Basic
    @Column(name = "home_phone_number")
    private String homePhoneNumber;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "phone_book", inverseJoinColumns = @JoinColumn(name = "user_id"),
            joinColumns = @JoinColumn(name = "contact_id"))
    private Set<User> users;

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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", email='" + email + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", homePhoneNumber='" + homePhoneNumber + '\'' +
                '}';
    }
}
