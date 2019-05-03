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

import tn.iset.model.tirage.Recharge;
import tn.iset.model.tirage.Tirage;
import tn.iset.reopsitory.tirage.TirageRepository;



@CrossOrigin("*")

@RestController
@RequestMapping("/tirage")

public class TirageController  {

	@Autowired
	private TirageRepository tirageRepository ;
	@Autowired
	private EntityManager entityManager;

	public TirageController (TirageRepository tirageRepository) {
		super();
		this.tirageRepository = tirageRepository;
	}
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public List<Tirage> getAll() {
		
		return tirageRepository.findAll();
	}
   
	@GetMapping("/{id}")
	public Tirage get(@PathVariable Long id) {
		
		return tirageRepository.findById(id).get();
	}
	
	  @PutMapping("/{id}")
	    public ResponseEntity<Recharge> put(@PathVariable Long id, @RequestBody Tirage tirage) {
	       Optional<Tirage> TirageOptional = tirageRepository.findById(id);

		if (!TirageOptional.isPresent())
			return ResponseEntity.notFound().build();

		tirage.setId(id);
		
		tirageRepository.save(tirage);
		 
		return ResponseEntity.noContent().build();
	    }
	  
	    @PostMapping
	    public void post(@Valid @RequestBody Tirage tirage) {
	    	tirageRepository.save(tirage);

	    }
	    
	    @DeleteMapping("/{id}")
	    public void delete(@PathVariable Long id) {
	    	tirageRepository.deleteById(id);
	    }
	    
@GetMapping("/history")
@ResponseBody
public List gethistory(){
	List revisions = AuditReaderFactory.get(entityManager)
           .createQuery()
           .forRevisionsOfEntity(Tirage.class, false, true)
           //.addProjection(AuditEntity.id())
           .addProjection( AuditEntity.revisionProperty("timestamp"))
           .addProjection(AuditEntity.revisionProperty("modifiedBy"))
           .addProjection(AuditEntity.revisionType())
           .getResultList();
	
	return revisions;
}}