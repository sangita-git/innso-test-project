package innso.test.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

import innso.test.api.entity.ClientCase;
import innso.test.api.service.InnsoTestServiceImpl;

/* Rest-Controller class to operate on ClientCase-entity */
@RestController
@RequestMapping("/clientcases")
public class ClientCasesController {
	
	@Autowired
	private InnsoTestServiceImpl service;
	
	/*
	 * Method to create a client-case
	 * URL: http://localhost:8080/clientcases
	 * Body:
	 * {
         "clientName":"Jérémie Durand",
	     "message":[{"id":101}]
	   }
	 */	
	@PostMapping
	public ResponseEntity<ClientCase> createClientCase(
			@Valid @RequestBody ClientCase clientCase) {
		return ResponseEntity.status(HttpStatus.CREATED)
				             .body(service.createClientCase(clientCase));
	}
	
	/*
	 * Method to fetch all client cases
	 * URL: http://localhost:8080/clientcases
	 */
	@GetMapping
	public ResponseEntity<List<ClientCase>> getAllClientCases() {
		return ResponseEntity.status(HttpStatus.OK)
				             .body(service.getAllClientCases());
	}
	
	/*
	 * Method to update a client-case record partially
	 * URL: http://localhost:8080/clientcases/{clientcase-id}
	 */
	@PatchMapping(path = "/{clientcase-id}", 
			consumes = "application/json-patch+json")
	public ResponseEntity<ClientCase> updateClientCase(
			@PathVariable("clientcase-id") Long id, 
			@RequestBody JsonPatch jsonPatch) {
		try {
			return ResponseEntity.status(HttpStatus.OK)
					             .body(service.updateClientCase(id, jsonPatch));
		} catch (JsonPatchException | JsonProcessingException e) {
			System.out.println(e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	}

}
