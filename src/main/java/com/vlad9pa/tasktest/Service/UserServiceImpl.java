package com.vlad9pa.tasktest.Service;

import com.vlad9pa.tasktest.Entity.Roles;
import com.vlad9pa.tasktest.Entity.User;
import com.vlad9pa.tasktest.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.HashSet;
import java.util.Set;

import static com.sun.jmx.snmp.SnmpStatusException.readOnly;

@Transactional
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserbyUsername(username);
    }

    @Override
    public void save(User user) {
        Set<Roles> roles = new HashSet<Roles>();
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }
}
