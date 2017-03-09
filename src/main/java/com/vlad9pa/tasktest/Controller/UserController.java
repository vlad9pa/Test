package com.vlad9pa.tasktest.Controller;

import com.vlad9pa.tasktest.Entity.Contact;
import com.vlad9pa.tasktest.Entity.Roles;
import com.vlad9pa.tasktest.Entity.User;
import com.vlad9pa.tasktest.Service.SecurityService;
import com.vlad9pa.tasktest.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @RequestMapping(value = "/profile/{username}")
    private @ResponseBody User getUser(@PathVariable(name = "username" )String username){
        return userService.findUserByUsername(username);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    private String login(@RequestParam(value = "logout", required = false) String logout,
                         @RequestParam(value = "error", required = false) String error){
        return "login";
    }
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    private String registration(Model model){
        model.addAttribute("user",new User());
        return "registration";
    }
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    private String registration(@ModelAttribute(name = "user") User user, Model model){
        userService.save(user);
        return "redirect:/user/login";
    }
    @RequestMapping(value = "/phonebook", method = RequestMethod.GET)
    private String phoneBook(Model model){
        model.addAttribute("contact", new Contact());
        return "phoneBook";
    }
    @RequestMapping(value = "/phonebook", method = RequestMethod.POST)
    private String phoneBook(@ModelAttribute(name = "contact") Contact contact){
        userService.addToPhoneBook(userService.findUserByUsername("123"),contact);
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
