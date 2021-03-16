package innso.test.api.service;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

import innso.test.api.entity.ClientCase;
import innso.test.api.entity.Message;

public interface InnsoTestService {

	public Message createMessage(Message msg);
	
	public ClientCase createClientCase(ClientCase clientCase); 
	
	public List<ClientCase> getAllClientCases();
	
	public ClientCase updateClientCase(
			Long id, JsonPatch jsonPatch) 
					throws JsonPatchException, JsonProcessingException;

}
