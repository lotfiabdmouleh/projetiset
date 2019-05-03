package tn.iset.controller.tirage;


import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.validation.Valid;

import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.iset.model.tirage.AgentTirage;
import tn.iset.reopsitory.tirage.AgentTirageRepository;

@CrossOrigin("*")

@RestController
@RequestMapping("/agentTirage")

public class AgentTirageController {

	@Autowired
	private AgentTirageRepository agentRepository;
	@Autowired
	private EntityManager entityManager;

	public AgentTirageController(AgentTirageRepository agentRepository) {
		super();
		this.agentRepository = agentRepository;
	}
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public List<AgentTirage> getAll() {
		
		return agentRepository.findAll();
	}
    
	@GetMapping("/{id}")
	public AgentTirage get(@PathVariable Long id) {
		
		return agentRepository.findById(id).get();
	}
	
	  @PutMapping("/{id}")
	    public ResponseEntity<AgentTirage> put(@PathVariable Long id, @RequestBody AgentTirage agent ) {
	       Optional<AgentTirage> agentOptional = agentRepository.findById(id);

		if (!agentOptional.isPresent())
			return ResponseEntity.notFound().build();

		agent.setId(id);
		
		agentRepository.save(agent);
		 
		return ResponseEntity.noContent().build();
	    }
	  
	    @PostMapping
	    public void post(@Valid @RequestBody AgentTirage agent) {
	    		agentRepository.save(agent);

	    }
	    
	    @DeleteMapping("/{id}")
	    public void delete(@PathVariable Long id) {
	        agentRepository.deleteById(id);
	    }
	    
@GetMapping("/history")
@ResponseBody
public List gethistory(){
	List revisions = AuditReaderFactory.get(entityManager)
            .createQuery()
            .forRevisionsOfEntity(AgentTirage.class, false, true)
            //.addProjection(AuditEntity.id())
            .addProjection( AuditEntity.revisionProperty("timestamp"))
            .addProjection(AuditEntity.revisionProperty("modifiedBy"))
            .addProjection(AuditEntity.revisionType())
            .getResultList();
	
	return revisions;
}
	

}
