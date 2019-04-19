package tn.iset.controller;


import java.util.List;
import java.util.Optional;

import javax.persistence.PreUpdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.iset.model.Agent;
import tn.iset.repository.AgentRepository;
@CrossOrigin("*")

@RestController
@RequestMapping("/agent")

public class AgentController {
	@Autowired
	private AgentRepository agentRepository;

	public AgentController(AgentRepository agentRepository) {
		super();
		this.agentRepository = agentRepository;
	}
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public List<Agent> getAll() {
	 
		return agentRepository.findAll();
	}
    
	@GetMapping("/{id}")
	public Agent get(@PathVariable Long id) {
		
		return agentRepository.findById(id).get();
	}
	
	  @PutMapping("/{id}")
	    public ResponseEntity<Agent> put(@PathVariable Long id, @RequestBody Agent agent ) {
	       Optional<Agent> agentOptional = agentRepository.findById(id);

		if (!agentOptional.isPresent())
			return ResponseEntity.notFound().build();

		agent.setId(id);
		
		agentRepository.save(agent);
		 
		return ResponseEntity.noContent().build();
	    }
	  
	    @PostMapping
	    public void post(@RequestBody Agent agent) {
	    		agentRepository.save(agent);

	    }
	    
	    @DeleteMapping("/{id}")
	    public void delete(@PathVariable Long id) {
	        agentRepository.deleteById(id);
	    }
	   

	

}
