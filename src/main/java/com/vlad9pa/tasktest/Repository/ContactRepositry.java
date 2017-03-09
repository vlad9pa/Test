package com.vlad9pa.tasktest.Repository;

import com.vlad9pa.tasktest.Entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepositry extends JpaRepository<Contact, Long> {
}
