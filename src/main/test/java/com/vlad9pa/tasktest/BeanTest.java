package com.vlad9pa.tasktest;

import com.vlad9pa.tasktest.comporator.ContactComporator;
import com.vlad9pa.tasktest.service.ContactService;
import com.vlad9pa.tasktest.service.SecurityService;
import com.vlad9pa.tasktest.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@AutoConfigureDataJpa
@SpringBootTest
public class BeanTest {
    @Autowired
    private ContactComporator contactComporator;
    @Autowired
    private ContactService contactService;
    @Autowired
    private UserService userService;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void beansNotEmpty(){
        assertThat(contactComporator).isNotNull();
        assertThat(contactService).isNotNull();
        assertThat(userService).isNotNull();
        assertThat(securityService).isNotNull();
        assertThat(bCryptPasswordEncoder).isNotNull();
    }

}
