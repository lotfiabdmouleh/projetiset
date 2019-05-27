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

import tn.iset.model.tirage.Ancre;
import tn.iset.reopsitory.tirage.AncreRepository;

 @CrossOrigin("*")

@RestController
@RequestMapping("/ancre")

 public class AncreController {

	@Autowired
	private AncreRepository ancrerepo;
	@Autowired
	private EntityManager entityManager;

	public AncreController(AncreRepository ancrerepo) {
		super();
		this.ancrerepo = ancrerepo;
	}
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')or hasRole('AGENT')")
	public List<Ancre> getAll() {
		
		return ancrerepo.findAll();
	}
    
	@GetMapping("/{id}")
	public Ancre get(@PathVariable Long id) {
		
		return ancrerepo.findById(id).get();
	}
	
	  @PutMapping("/{id}")
	    public ResponseEntity<Ancre> put(@PathVariable Long id, @RequestBody Ancre ancre ) {
	       Optional<Ancre> ancreOptional = ancrerepo.findById(id);

		if (!ancreOptional.isPresent())
			return ResponseEntity.notFound().build();

		ancre.setId(id);
		
		ancrerepo.save(ancre);
		 
		return ResponseEntity.noContent().build();
	    }
	  
	    @PostMapping
	    public void post(@Valid @RequestBody Ancre ancre) {
	    		ancrerepo.save(ancre);

	    }
	    
	    @DeleteMapping("/{id}")
	    public void delete(@PathVariable Long id) {
	        ancrerepo.deleteById(id);
	    }
			    
		@GetMapping("/history")
		@ResponseBody
		public List gethistory(){
			List revisions = AuditReaderFactory.get(entityManager)
		            .createQuery()
		            .forRevisionsOfEntity(Ancre.class, false, true)
		            //.addProjection(AuditEntity.id())
		            .addProjection( AuditEntity.revisionProperty("timestamp"))
		            .addProjection(AuditEntity.revisionProperty("modifiedBy"))
		            .addProjection(AuditEntity.revisionType())
		            .getResultList();
			
			return revisions;
		}
	

}

