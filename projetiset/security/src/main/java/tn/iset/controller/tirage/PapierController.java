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

import tn.iset.model.tirage.Papier;
import tn.iset.reopsitory.tirage.PapierRepository;


@CrossOrigin("*")

@RestController
@RequestMapping("/papier")

public class PapierController  {

	@Autowired
	private PapierRepository papierRepository ;
	@Autowired
	private EntityManager entityManager;

	public PapierController ( PapierRepository papierRepository) {
		super();
		this.papierRepository = papierRepository;
	}
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')or hasRole('AGENT')")
	public List<Papier> getAll() {
		
		return papierRepository.findAll();
	}
   
	@GetMapping("/{id}")
	public Papier get(@PathVariable Long id) {
		
		return papierRepository.findById(id).get();
	}
	
	  @PutMapping("/{id}")
	    public ResponseEntity<Papier> put(@PathVariable Long id, @RequestBody Papier papier) {
	       Optional<Papier> PapierOptional = papierRepository.findById(id);

		if (!PapierOptional.isPresent())
			return ResponseEntity.notFound().build();

		papier.setId(id);
		
		papierRepository.save(papier);
		 
		return ResponseEntity.noContent().build();
	    }
	  
	    @PostMapping
	    public void post(@Valid @RequestBody Papier papier) {
	    	papierRepository.save(papier);

	    }
	    
	    @DeleteMapping("/{id}")
	    public void delete(@PathVariable Long id) {
	    	papierRepository.deleteById(id);
	    }
	    
@GetMapping("/history")
@ResponseBody
public List gethistory(){
	List revisions = AuditReaderFactory.get(entityManager)
           .createQuery()
           .forRevisionsOfEntity(Papier.class, false, true)
           //.addProjection(AuditEntity.id())
           .addProjection( AuditEntity.revisionProperty("timestamp"))
           .addProjection(AuditEntity.revisionProperty("modifiedBy"))
           .addProjection(AuditEntity.revisionType())
           .getResultList();
	
	return revisions;
}}