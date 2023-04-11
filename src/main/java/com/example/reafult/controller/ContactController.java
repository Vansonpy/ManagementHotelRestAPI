package com.example.reafult.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.reafult.dto.PagesDTO;
import com.example.reafult.entities.Contact;
import com.example.reafult.services.ContactService;

@RestController
@RequestMapping("/api/contact")
public class ContactController {
	@Autowired
	private ContactService contactService;

	@PostMapping(value = "/addContact")
	public ResponseEntity<String> addRoom(@RequestBody Contact contact) throws Exception {
		try {
			Contact contactInput = contactService.save(contact);
			if (contactInput != null) {
				return new ResponseEntity<String>("Thank you", HttpStatus.OK);
			}
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/viewAllContact")
	public ResponseEntity<List<Contact>> viewAllContact()
			throws Exception {
		try {
			List<Contact> listContact = contactService.findAll();
			if (listContact.isEmpty()) {
				return new ResponseEntity<List<Contact>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Contact>>(listContact, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Contact>>(HttpStatus.BAD_REQUEST);
		}
	}
}
