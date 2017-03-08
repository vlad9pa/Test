package com.vlad9pa.tasktest.Controller;

import com.vlad9pa.tasktest.Entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    private String login(){
        return "login";
    }
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    private String registration(Model model){
        model.addAttribute(new User());
        return "registration";
    }
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    private String registration(@ModelAttribute(name = "userForm") User userForm, Model model){
        return "redirect:/login";
    }
    @RequestMapping(value = "/phonebook", method = RequestMethod.GET)
    private String phoneBook(){
        return "phoneBook";
    }
    @RequestMapping(value = "/phonebook", method = RequestMethod.PUT)
    private String addContactToBook(){
        return "phoneBook";
    }
    @RequestMapping(value = "/phonebook", method = RequestMethod.DELETE)
    private String deleteContactFromBook(){
        return "phoneBook";
    }
}
