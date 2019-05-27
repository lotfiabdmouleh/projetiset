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
import tn.iset.model.tirage.Intervention;
import tn.iset.model.tirage.Photocopieur;
import tn.iset.model.tirage.Recharge;
import tn.iset.reopsitory.tirage.AncreRepository;
import tn.iset.reopsitory.tirage.InterventionRepository;
import tn.iset.reopsitory.tirage.PhotocopieurRepository;
import tn.iset.reopsitory.tirage.RechargeRepository;


@CrossOrigin("*")

@RestController
@RequestMapping("/intervention")

public class InterventionController  {

	@Autowired
	private InterventionRepository interventionRepository;

	@Autowired
	private PhotocopieurRepository photocopieurRepository;
	@Autowired
	
	private EntityManager entityManager;

	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public List<Intervention> getAll() {
		
		return interventionRepository.findAll();
	}
   
	@GetMapping("/{id}")
	public Intervention get(@PathVariable Long id) {
		
		return interventionRepository.findById(id).get();
	}
	
	  @PutMapping("/{id}/{ph}")
	    public ResponseEntity<Recharge> put(@PathVariable Long id,@PathVariable Long ph, @RequestBody Intervention  intervention) {
	       Optional<Intervention> InterventionOptional = interventionRepository.findById(id);

		if (!InterventionOptional.isPresent())
			return ResponseEntity.notFound().build();
	
		
    	Photocopieur f=photocopieurRepository.findById(ph).get();
    
    	intervention.setPhotocopieur(f);
    	intervention.setId(id);
    	
    	interventionRepository.save(intervention);
		 
		return ResponseEntity.noContent().build();
	    }
	  
	    @PostMapping("/{ph}")
	    public void post(@Valid @PathVariable Long ph, @RequestBody Intervention intervention) {
	    
	    	Photocopieur f=photocopieurRepository.findById(ph).get();
	    	f.addIntervention(intervention);
	    	intervention.setPhotocopieur(f);
	    		
	    	interventionRepository.save(intervention);
	    }
	    
	    @DeleteMapping("/{id}")
	    public void delete(@PathVariable Long id) {
	    	interventionRepository.deleteById(id);
	    }
	    
		@GetMapping("/history")
		@ResponseBody
		public List gethistory(){
			List revisions = AuditReaderFactory.get(entityManager)
		           .createQuery()
		           .forRevisionsOfEntity(Recharge.class, false, true)
		           //.addProjection(AuditEntity.id())
		           .addProjection( AuditEntity.revisionProperty("timestamp"))
		           .addProjection(AuditEntity.revisionProperty("modifiedBy"))
		           .addProjection(AuditEntity.revisionType())
		      
		           .getResultList();
			
			return revisions;
		}
}