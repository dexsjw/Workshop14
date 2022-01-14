package nus.iss.workshop14.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import nus.iss.workshop14.model.Contact;
import nus.iss.workshop14.repository.ContactsRepo;

@Service
public class ContactsRedis implements ContactsRepo {
    private static final Logger logger = Logger.getLogger(ContactsRedis.class.getName());

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Override
    public void save(final Contact contact) {
        redisTemplate.opsForValue().set(contact.getId(), contact);
    }

    @Override
    public Contact getById(final String contactId) {
        Contact contact = (Contact)redisTemplate.opsForValue().get(contactId);
        logger.log(Level.INFO, contact.getEmail());
        return contact;
    }

}
