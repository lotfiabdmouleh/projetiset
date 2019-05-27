package tn.iset.controller;




import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.validation.Valid;

import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.criteria.AuditCriterion;
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

import tn.iset.model.Agent;
import tn.iset.repository.AgentRepository;
@CrossOrigin("*")

@RestController
@RequestMapping("/agent")

public class AgentController {

	@Autowired
	private AgentRepository agentRepository;
	@Autowired
	private EntityManager entityManager;

	public AgentController(AgentRepository agentRepository) {
		super();
		this.agentRepository = agentRepository;
	}
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')or hasRole('PM') or hasRole('AGENT')")
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
	    public void post(@Valid @RequestBody Agent agent) {
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
            .forRevisionsOfEntity(Agent.class, false, true)
            .addProjection(AuditEntity.id())
            .addProjection( AuditEntity.revisionProperty("timestamp"))
            .addProjection(AuditEntity.revisionProperty("modifiedBy"))
            .addProjection(AuditEntity.revisionType())
            .getResultList();
	
	return revisions;
}
	@GetMapping("/historyDetail/{id}")
	@ResponseBody
	public List getHistoryDetail(@PathVariable Long id){
		
	return	entityManager.createNativeQuery("select r.modified_by,r.revtstmp,a.nom,a.prenom from revinfo r,agent_aud a where a.rev=r.rev and a.id=?")
		.setParameter(1, id)
		.getResultList();
	}

}
