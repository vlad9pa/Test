package com.vlad9pa.tasktest;

import com.vlad9pa.tasktest.controller.MainController;
import com.vlad9pa.tasktest.controller.UserController;
import com.vlad9pa.tasktest.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ControllerTest {

    @Autowired
    private UserController userController;

    @Autowired
    private MainController mainController;

    @Test
    public void contextLoads() throws Exception{
        assertThat(mainController).isNotNull();
        assertThat(userController).isNotNull();
    }
}
