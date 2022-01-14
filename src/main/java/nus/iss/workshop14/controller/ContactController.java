package nus.iss.workshop14.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import nus.iss.workshop14.model.Contact;
import nus.iss.workshop14.service.ContactsRedis;

@Controller
public class ContactController {
    private static final Logger logger = LoggerFactory.getLogger(ContactController.class);
    
    @Autowired
    ContactsRedis contactsService;

    @GetMapping("/")
    public String contactForm(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact";
    }

    @GetMapping("/contact/{contactId}")
    public String getContact(@PathVariable(value = "contactId") String contactId, Model model) {
        logger.info("ContactId: " + contactId);
        Contact getContact = contactsService.getById(contactId);
        model.addAttribute("contact", getContact);
        return "contactDetails";
    }

    @PostMapping("/contact")
    public String contactSaving(@ModelAttribute Contact contact, Model model) {
        Contact newContact = new Contact(contact.getName(), contact.getEmail(), contact.getPhoneNum());
        logger.info("Name: " + newContact.getName());
        logger.info("Email: " + newContact.getEmail());
        logger.info("Phone Number: " + newContact.getPhoneNum());
        //logger.info("ID: " + newContact.getId());
        contactsService.save(newContact);
        model.addAttribute("contact", newContact);
        return "contactDetails";
    }
    
}
