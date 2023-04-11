package com.example.reafult.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.reafult.entities.Contact;
import com.example.reafult.repository.ContactRepository;

@Service
@Transactional
public class ContactService {

	@Autowired
	private ContactRepository contactRepository;
	
	public Contact findById(final Integer id) {
		Contact contact = contactRepository.findById(id).get();
		return contact;
	}
	
	public List<Contact> findAll() {
		List<Contact> listContact = contactRepository.findAll();
		return listContact;
	}
	
	
	public Contact save(Contact contact) {
		Contact contactSave = contactRepository.save(contact);
		return contactSave;
	}
	
	public void delete(final Integer id) {
		Contact contact =contactRepository.findById(id).get();
		if (contact != null) {
			contactRepository.delete(contact);
		}
	}
}
