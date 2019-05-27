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
import tn.iset.model.tirage.DemandeTirage;
import tn.iset.model.tirage.Semestre;
import tn.iset.reopsitory.tirage.SemestreRepository;

@CrossOrigin("*")

@RestController
@RequestMapping("/semestre")

public class SemestreController {

	@Autowired
	private SemestreRepository semestreRepository;
	@Autowired
	private EntityManager entityManager;

	@GetMapping
	@PreAuthorize("hasRole('ADMIN')or hasRole('PM')")
	public List<Semestre> getAll() {
		
		return semestreRepository.findAll();
	}
    
	@GetMapping("/{id}")
	public Semestre get(@PathVariable Long id) {
		
		return semestreRepository.findById(id).get();
	}
	 @PutMapping("/{id}")
	    public ResponseEntity<DemandeTirage> put(@PathVariable Long id, @RequestBody Semestre semestre ) {
	       Optional<Semestre> semestreOptional = semestreRepository.findById(id);

		if (!semestreOptional.isPresent())
			return ResponseEntity.notFound().build();

		semestre.setId(id);
		semestreRepository.save(semestre);
		 
		return ResponseEntity.noContent().build();
	    }
	  
	    @PostMapping
	    public void post(@Valid @RequestBody Semestre semestre) {
	    	semestreRepository.save(semestre);

	    }

	  
	    @DeleteMapping("/{id}")
	    public void delete(@PathVariable Long id) {
	    	semestreRepository.deleteById(id);
	    }
	    
@GetMapping("/history")
@ResponseBody
public List gethistory(){
	List revisions = AuditReaderFactory.get(entityManager)
            .createQuery()
            .forRevisionsOfEntity(Semestre.class, false, true)
            //.addProjection(AuditEntity.id())
            .addProjection( AuditEntity.revisionProperty("timestamp"))
            .addProjection(AuditEntity.revisionProperty("modifiedBy"))
            .addProjection(AuditEntity.revisionType())
            .getResultList();
	
	return revisions;
}
}
