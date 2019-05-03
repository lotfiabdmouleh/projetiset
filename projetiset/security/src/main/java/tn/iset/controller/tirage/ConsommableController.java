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

import tn.iset.model.tirage.Consommable;
import tn.iset.reopsitory.tirage.ConsommableRepository;

 
@CrossOrigin("*")

@RestController
@RequestMapping("/consommable")

public class ConsommableController  {

	@Autowired
	private ConsommableRepository consommableRepository;
	@Autowired
	private EntityManager entityManager;

	public ConsommableController (ConsommableRepository consommableRepository) {
		super();
		this.consommableRepository = consommableRepository;
	}
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public List<Consommable> getAll() {
		
		return consommableRepository.findAll();
	}
   
	@GetMapping("/{id}")
	public Consommable get(@PathVariable Long id) {
		
		return consommableRepository.findById(id).get();
	}
	
	  @PutMapping("/{id}")
	    public ResponseEntity<Consommable> put(@PathVariable Long id, @RequestBody Consommable consommable ) {
	       Optional<Consommable> consommableOptional = consommableRepository.findById(id);

		if (!consommableOptional.isPresent())
			return ResponseEntity.notFound().build();

		consommable.setId(id);
		
		consommableRepository.save(consommable);
		 
		return ResponseEntity.noContent().build();
	    }
	  
	    @PostMapping
	    public void post(@Valid @RequestBody Consommable consommable) {
	    	consommableRepository.save(consommable);

	    }
	    
	    @DeleteMapping("/{id}")
	    public void delete(@PathVariable Long id) {
	    	consommableRepository.deleteById(id);
	    }
	    
		@GetMapping("/history")
		@ResponseBody
		public List gethistory(){
			List revisions = AuditReaderFactory.get(entityManager)
		           .createQuery()
		           .forRevisionsOfEntity(Consommable.class, false, true)
		           //.addProjection(AuditEntity.id())
		           .addProjection( AuditEntity.revisionProperty("timestamp"))
		           .addProjection(AuditEntity.revisionProperty("modifiedBy"))
		           .addProjection(AuditEntity.revisionType())
		           .getResultList();
			
			return revisions;
		}
	

}

