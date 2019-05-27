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

import tn.iset.model.tirage.AgentTirage;
import tn.iset.model.tirage.Annee;
import tn.iset.model.tirage.DemandeTirage;
import tn.iset.model.tirage.Semestre;
import tn.iset.reopsitory.tirage.AnneeRepository;
import tn.iset.reopsitory.tirage.SemestreRepository;


@CrossOrigin("*")

@RestController
@RequestMapping("/annee")

public class AnneeController {

	@Autowired
	private AnneeRepository anneeRepository;
	@Autowired
	private EntityManager entityManager;

	@GetMapping
	@PreAuthorize("hasRole('ADMIN')or hasRole('PM')or hasRole('AGENT')")
	public List<Annee> getAll() {
		
		return anneeRepository.findAll();
	}
    
	@GetMapping("/{id}")
	public Annee get(@PathVariable Long id) {
		
		return anneeRepository.findById(id).get();
	}
	 @PutMapping("/{id}")
	    public ResponseEntity<Annee> put(@PathVariable Long id, @RequestBody Annee annee ) {
	       Optional<Annee> anneeOptional = anneeRepository.findById(id);

		if (!anneeOptional.isPresent())
			return ResponseEntity.notFound().build();

		annee.setId(id);
		anneeRepository.save(annee);
		 
		return ResponseEntity.noContent().build();
	    }
	  
	    @PostMapping
	    public void post(@Valid @RequestBody Annee annee) {
	    	anneeRepository.save(annee);

	    }

	  
	    @DeleteMapping("/{id}")
	    public void delete(@PathVariable Long id) {
	    	anneeRepository.deleteById(id);
	    }
	    
@GetMapping("/history")
@ResponseBody
public List gethistory(){
	List revisions = AuditReaderFactory.get(entityManager)
            .createQuery()
            .forRevisionsOfEntity(Annee.class, false, true)
            //.addProjection(AuditEntity.id())
            .addProjection( AuditEntity.revisionProperty("timestamp"))
            .addProjection(AuditEntity.revisionProperty("modifiedBy"))
            .addProjection(AuditEntity.revisionType())
            .getResultList();
	
	return revisions;
}
}
