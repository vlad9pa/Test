package com.vlad9pa.tasktest.Repository;

import com.vlad9pa.tasktest.Entity.PhoneBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneBookRepository extends JpaRepository<PhoneBook, Long>{
}
