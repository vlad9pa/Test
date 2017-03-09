package com.vlad9pa.tasktest.Controller;

import com.vlad9pa.tasktest.Entity.Contact;
import com.vlad9pa.tasktest.Entity.User;
import com.vlad9pa.tasktest.Service.ContactService;
import com.vlad9pa.tasktest.Service.SecurityService;
import com.vlad9pa.tasktest.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ContactService contactService;

    @Autowired
    private SecurityService securityService;

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
    private String registration(@ModelAttribute(name = "user") @Valid User user,
                                BindingResult bindingResult,
                                Model model){
        if(bindingResult.hasErrors()){
            return "registration";
        }
        userService.save(user);
        return "redirect:/user/login";
    }

    @RequestMapping(value = "/phonebook", method = RequestMethod.GET)
    private String phoneBook(Model model){
        User user = userService.findByUsername(securityService.findLoggedInUsername());
        model.addAttribute("contacts",user.getContacts());
        return "phone_book";
    }

    @RequestMapping(value = "/phonebook/add", method = RequestMethod.GET)
    private String addContactToBook(Model model){
        model.addAttribute("contact", new Contact());
        return "contact_creator";
    }

    @RequestMapping(value = "/phonebook/add", method = RequestMethod.POST)
    private String addContactToBook(@ModelAttribute(name = "contact") @Valid Contact contact,
                                    BindingResult bindingResult,
                                    Model model){
        if(bindingResult.hasErrors()){
            return "contact_creator";
        }
        User user = userService.findByUsername(securityService.findLoggedInUsername());
        contactService.save(user,contact);
        return "redirect:/user/phonebook";
    }

    @RequestMapping(value = "/phonebook/edit/{id}", method = RequestMethod.GET)
    private String editContact(@PathVariable(name = "id") Long id,
                               Model model){
        model.addAttribute("contact", contactService.findById(id));
        return "contact_editor";
    }
    @RequestMapping(value = "/phonebook/edit/{id}", method = RequestMethod.PUT)
    private String updateContact(@PathVariable(name = "id") Long id,
                                 @ModelAttribute(name = "contact") @Valid Contact contact,
                                 BindingResult bindingResult,
                                 Model model){
        /*if(bindingResult.hasErrors()){
            return "contact_editor";
        }*/
        contact.setId(id);
        System.out.println(contact.toString());
        contactService.update(contact);
        return "redirect:/user/phonebook";
    }

    @RequestMapping(value = "/phonebook/edit", method = RequestMethod.DELETE)
    private String deleteContact(@ModelAttribute(name = "contact") Contact contact){
        contactService.delete(contact);
        return "phone_book";
    }
}
