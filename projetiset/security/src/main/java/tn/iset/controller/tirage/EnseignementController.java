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

import tn.iset.model.tirage.Enseignement;
import tn.iset.reopsitory.tirage.EnseignementRepository;



@CrossOrigin("*")

@RestController
@RequestMapping("/enseignement")

public class EnseignementController  {

	@Autowired
	private EnseignementRepository enseignementRepository ;
	@Autowired
	private EntityManager entityManager;

	public EnseignementController ( EnseignementRepository enseignementRepository) {
		super();
		this.enseignementRepository = enseignementRepository;
	}
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public List<Enseignement> getAll() {
		
		return enseignementRepository.findAll();
	}
   
	@GetMapping("/{id}")
	public Enseignement get(@PathVariable Long id) {
		
		return enseignementRepository.findById(id).get();
	}
	
	  @PutMapping("/{id}")
	    public ResponseEntity<Enseignement> put(@PathVariable Long id, @RequestBody Enseignement enseignement) {
	       Optional<Enseignement> EnseignementOptional = enseignementRepository.findById(id);

		if (!EnseignementOptional.isPresent())
			return ResponseEntity.notFound().build();

		enseignement.setId(id);
		
		enseignementRepository.save(enseignement);
		 
		return ResponseEntity.noContent().build();
	    }
	  
	    @PostMapping
	    public void post(@Valid @RequestBody Enseignement dept) {
	    	enseignementRepository.save(dept);

	    }
	    
	    @DeleteMapping("/{id}")
	    public void delete(@PathVariable Long id) {
	    	enseignementRepository.deleteById(id);
	    }
	    
@GetMapping("/history")
@ResponseBody
public List gethistory(){
	List revisions = AuditReaderFactory.get(entityManager)
           .createQuery()
           .forRevisionsOfEntity(Enseignement.class, false, true)
           //.addProjection(AuditEntity.id())
           .addProjection( AuditEntity.revisionProperty("timestamp"))
           .addProjection(AuditEntity.revisionProperty("modifiedBy"))
           .addProjection(AuditEntity.revisionType())
           .getResultList();
	
	return revisions;
}}
	