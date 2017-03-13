package com.vlad9pa.tasktest.repository;

import com.vlad9pa.tasktest.entity.Contact;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Profile("sql")
@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {
    Contact findByFirstName(String firstName);
}
