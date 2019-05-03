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

import tn.iset.model.tirage.Divers;
import tn.iset.reopsitory.tirage.DiversRepository;


@CrossOrigin("*")

@RestController
@RequestMapping("/divers")

public class DiversController  {

	@Autowired
	private DiversRepository diversRepository ;
	@Autowired
	private EntityManager entityManager;

	public DiversController ( DiversRepository diversRepository) {
		super();
		this.diversRepository = diversRepository;
	}
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public List<Divers> getAll() {
		
		return diversRepository.findAll();
	}
   
	@GetMapping("/{id}")
	public Divers get(@PathVariable Long id) {
		
		return diversRepository.findById(id).get();
	}
	
	  @PutMapping("/{id}")
	    public ResponseEntity<Divers> put(@PathVariable Long id, @RequestBody Divers divers ) {
	       Optional<Divers> diversOptional = diversRepository.findById(id);

		if (!diversOptional.isPresent())
			return ResponseEntity.notFound().build();

		divers.setId(id);
		
		diversRepository.save(divers);
		 
		return ResponseEntity.noContent().build();
	    }
	  
	    @PostMapping
	    public void post(@Valid @RequestBody Divers divers) {
	    	diversRepository.save(divers);

	    }
	    
	    @DeleteMapping("/{id}")
	    public void delete(@PathVariable Long id) {
	    	diversRepository.deleteById(id);
	    }
	    
		@GetMapping("/history")
		@ResponseBody
		public List gethistory(){
			List revisions = AuditReaderFactory.get(entityManager)
		           .createQuery()
		           .forRevisionsOfEntity(Divers.class, false, true)
		           //.addProjection(AuditEntity.id())
		           .addProjection( AuditEntity.revisionProperty("timestamp"))
		           .addProjection(AuditEntity.revisionProperty("modifiedBy"))
		           .addProjection(AuditEntity.revisionType())
		           .getResultList();
			
			return revisions;
		}}
			