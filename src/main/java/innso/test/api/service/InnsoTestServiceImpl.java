package innso.test.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

import innso.test.api.entity.Channel;
import innso.test.api.entity.ClientCase;
import innso.test.api.entity.Message;
import innso.test.api.repository.ChannelRepository;
import innso.test.api.repository.ClientCaseRepository;
import innso.test.api.repository.MessageRepository;

@Service
public class InnsoTestServiceImpl implements InnsoTestService {
	
	@Autowired
	ChannelRepository channelRep;

	@Autowired
	MessageRepository msgRep;
	
	@Autowired
	ClientCaseRepository clientCaseRep;

	/* Method to create a message */
	@Override
	public Message createMessage(Message msg) {
		//retrieve the channel-source and set it to 
		//message-entity
		Long channelId = msg.getChannel().getId();
		msg.setChannel(getChannel(channelId)); 
		//save the message
		return msgRep.save(msg);
	}

	/* Method to create a client-case */
	@Override
	public ClientCase createClientCase(ClientCase clientCase) {
		//retrieve the entire message-entity and set it to 
		//client-case-entity
		Message msg = clientCase.getMessage().get(0);
		msg = msgRep.findById(msg.getId()).get();
		List<Message> msgLst = new ArrayList<Message>();
		msgLst.add(msg);
		clientCase.setMessage(msgLst);
		//save the client-case
		return clientCaseRep.save(clientCase);
	}
	
	/* Method to fetch a specific channel-record 
	 * according to channel-id */
	private Channel getChannel(Long id) {
		return channelRep.findById(id).get();
	}

	/* Method to fetch all registered client-cases */
	@Override
	public List<ClientCase> getAllClientCases() {
		return clientCaseRep.findAll();
	}

	/* Method to update a client-case record partially */
	@Override
	public ClientCase updateClientCase(Long id, JsonPatch jsonPatch)
			throws JsonPatchException, JsonProcessingException {
		ClientCase clientCase = clientCaseRep.findById(id).get();
		int initSize = clientCase.getMessage().size(); //store the initial-size of the Message-list
		
		ObjectMapper objectMapper = new ObjectMapper();
		//register specific modules in order to 
		//Serialize and DeSerialize java.time.LocalDateTime
		objectMapper.findAndRegisterModules();
		
		//apply JSON patch to client-case-entity
		JsonNode patched = jsonPatch.apply(
				           objectMapper.convertValue(
				        		   clientCase, JsonNode.class));
		clientCase = objectMapper.treeToValue(patched, ClientCase.class);
		
		//in case a new Message has been added then 
		//retrieve the entire Message and update Message-List
		//in Client-Case-Entity
		List<Message> msgLst = clientCase.getMessage();
		if (initSize < clientCase.getMessage().size()) {
			Message msg = msgLst.remove(0);
			msg = msgRep.findById(msg.getId()).get();
			msgLst.add(msg);
			clientCase.setMessage(msgLst);
		}
		
		clientCaseRep.save(clientCase); //save() updates the already present client-case-entity
		return clientCase;
	}
	
	
}
