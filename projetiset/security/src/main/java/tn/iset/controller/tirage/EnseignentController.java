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

import tn.iset.model.tirage.Enseignant;
import tn.iset.reopsitory.tirage.EnseignantRepository;


@CrossOrigin("*")

@RestController
@RequestMapping("/enseignant")

public class EnseignentController  {

	@Autowired
	private EnseignantRepository enseignantRepository ;
	@Autowired
	private EntityManager entityManager;

	public EnseignentController ( EnseignantRepository enseignantRepository) {
		super();
		this.enseignantRepository = enseignantRepository;
	}
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public List<Enseignant> getAll() {
		
		return enseignantRepository.findAll();
	}
   
	@GetMapping("/{id}")
	public Enseignant get(@PathVariable Long id) {
		
		return enseignantRepository.findById(id).get();
	}
	
	  @PutMapping("/{id}")
	    public ResponseEntity<Enseignant> put(@PathVariable Long id, @RequestBody Enseignant enseignant ) {
	       Optional<Enseignant> EnseignantOptional = enseignantRepository.findById(id);

		if (!EnseignantOptional.isPresent())
			return ResponseEntity.notFound().build();

		enseignant.setId(id);
		
		enseignantRepository.save(enseignant);
		 
		return ResponseEntity.noContent().build();
	    }
	  
	    @PostMapping
	    public void post(@Valid @RequestBody Enseignant enseignant) {
	    	enseignantRepository.save(enseignant);

	    }
	    
	    @DeleteMapping("/{id}")
	    public void delete(@PathVariable Long id) {
	    	enseignantRepository.deleteById(id);
	    }
	    
@GetMapping("/history")
@ResponseBody
public List gethistory(){
	List revisions = AuditReaderFactory.get(entityManager)
           .createQuery()
           .forRevisionsOfEntity(Enseignant.class, false, true)
           //.addProjection(AuditEntity.id())
           .addProjection( AuditEntity.revisionProperty("timestamp"))
           .addProjection(AuditEntity.revisionProperty("modifiedBy"))
           .addProjection(AuditEntity.revisionType())
           .getResultList();
	
	return revisions;
}}
	