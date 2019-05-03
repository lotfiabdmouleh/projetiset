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

import tn.iset.model.tirage.Photocopieur;
import tn.iset.reopsitory.tirage.PhotocopieurRepository;


@CrossOrigin("*")

@RestController
@RequestMapping("/photocopieur")

public class PhotocopieurController  {

	@Autowired
	private PhotocopieurRepository photocopieurRepository ;
	@Autowired
	private EntityManager entityManager;

	public PhotocopieurController (PhotocopieurRepository photocopieurRepository ) {
		super();
		this.photocopieurRepository = photocopieurRepository;
	}
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public List<Photocopieur> getAll() {
		
		return photocopieurRepository.findAll();
	}
   
	@GetMapping("/{id}")
	public Photocopieur get(@PathVariable Long id) {
		
		return photocopieurRepository.findById(id).get();
	}
	
	  @PutMapping("/{id}")
	    public ResponseEntity<Photocopieur> put(@PathVariable Long id, @RequestBody Photocopieur photocopieur) {
	       Optional<Photocopieur> PhotocopieurOptional = photocopieurRepository.findById(id);

		if (!PhotocopieurOptional.isPresent())
			return ResponseEntity.notFound().build();

		photocopieur.setId(id);
		
		photocopieurRepository.save(photocopieur);
		 
		return ResponseEntity.noContent().build();
	    }
	  
	    @PostMapping
	    public void post(@Valid @RequestBody Photocopieur photocopieur) {
	    	photocopieurRepository.save(photocopieur);

	    }
	    
	    @DeleteMapping("/{id}")
	    public void delete(@PathVariable Long id) {
	    	photocopieurRepository.deleteById(id);
	    }
	    
		@GetMapping("/history")
		@ResponseBody
		public List gethistory(){
			List revisions = AuditReaderFactory.get(entityManager)
		           .createQuery()
		           .forRevisionsOfEntity(Photocopieur.class, false, true)
		           //.addProjection(AuditEntity.id())
		           .addProjection( AuditEntity.revisionProperty("timestamp"))
		           .addProjection(AuditEntity.revisionProperty("modifiedBy"))
		           .addProjection(AuditEntity.revisionType())
		           .getResultList();
			
			return revisions;
		}}