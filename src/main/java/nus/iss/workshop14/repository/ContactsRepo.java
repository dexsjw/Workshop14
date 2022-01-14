package nus.iss.workshop14.repository;

import nus.iss.workshop14.model.Contact;

public interface ContactsRepo {
    public void save(final Contact contact);            // why final?
    public Contact getById(final String contactId);     // to make sure that the variables are not mutable
}
