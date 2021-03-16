package innso.test.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import innso.test.api.entity.Message;
import innso.test.api.service.InnsoTestServiceImpl;

/* Rest-Controller class to operate on Message-entity */
@RestController
@RequestMapping("/messages")
public class MessageController {
	
	@Autowired
	private InnsoTestServiceImpl service;
	
	/*
	 * Method to create a message 
	 * URL: http://localhost:8080/messages
	 * Body: 
	 * {
         "authorName":"Jérémie Durand",
         "msgContent":"Hello, I have an issue with my new phone",
         "channel":{"id":1}
       }
	 */	
	@PostMapping
	public ResponseEntity<Message> createMessage(
			@Valid @RequestBody Message message) {
		return ResponseEntity.status(HttpStatus.CREATED)
				             .body(service.createMessage(message));
	}

}
