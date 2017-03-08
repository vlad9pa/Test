package com.vlad9pa.tasktest.Repository;

import com.vlad9pa.tasktest.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    User findUserByUsername(String username);
}
