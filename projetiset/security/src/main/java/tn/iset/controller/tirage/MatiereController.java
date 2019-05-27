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

import tn.iset.model.tirage.Matiere;
import tn.iset.reopsitory.tirage.MatiereRepository;



@CrossOrigin("*")

@RestController
@RequestMapping("/matiere")

public class MatiereController  {

	@Autowired
	private MatiereRepository matiereRepository ;
	@Autowired
	private EntityManager entityManager;

	public MatiereController ( MatiereRepository matiereRepository) {
		super();
		this.matiereRepository = matiereRepository;
	}
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')or hasRole('PM')or hasRole('AGENT')")
	public List<Matiere> getAll() {
		
		return matiereRepository.findAll();
	}
   
	@GetMapping("/{id}")
	public Matiere get(@PathVariable Long id) {
		
		return matiereRepository.findById(id).get();
	}
	
	  @PutMapping("/{id}")
	    public ResponseEntity<Matiere> put(@PathVariable Long id, @RequestBody Matiere matiere) {
	       Optional<Matiere> MatiereOptional = matiereRepository.findById(id);

		if (!MatiereOptional.isPresent())
			return ResponseEntity.notFound().build();

		matiere.setId(id);
		
		matiereRepository.save(matiere);
		 
		return ResponseEntity.noContent().build();
	    }
	  
	    @PostMapping
	    public void post(@Valid @RequestBody Matiere matiere) {
	    	matiereRepository.save(matiere);

	    }
	    
	    @DeleteMapping("/{id}")
	    public void delete(@PathVariable Long id) {
	    	matiereRepository.deleteById(id);
	    }
	    
		@GetMapping("/history")
		@ResponseBody
		public List gethistory(){
			List revisions = AuditReaderFactory.get(entityManager)
		           .createQuery()
		           .forRevisionsOfEntity(Matiere.class, false, true)
		           //.addProjection(AuditEntity.id())
		           .addProjection( AuditEntity.revisionProperty("timestamp"))
		           .addProjection(AuditEntity.revisionProperty("modifiedBy"))
		           .addProjection(AuditEntity.revisionType())
		           .getResultList();
			
			return revisions;
		}}