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

import tn.iset.model.tirage.DemandeTirage;
import tn.iset.reopsitory.tirage.DemandeTirageRepository;


@CrossOrigin("*")

@RestController
@RequestMapping("/demandetirage")

public class DemandeTirageController  {

	@Autowired
	private DemandeTirageRepository demandeTirageRepository;
	@Autowired
	private EntityManager entityManager;

	public DemandeTirageController (DemandeTirageRepository demandeTirageRepository) {
		super();
		this.demandeTirageRepository = demandeTirageRepository;
	}
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public List<DemandeTirage> getAll() {
		
		return demandeTirageRepository.findAll();
	}
   
	@GetMapping("/{id}")
	public DemandeTirage get(@PathVariable Long id) {
		
		return demandeTirageRepository.findById(id).get();
	}
	
	  @PutMapping("/{id}")
	    public ResponseEntity<DemandeTirage> put(@PathVariable Long id, @RequestBody DemandeTirage demandeTirage ) {
	       Optional<DemandeTirage> demandetirageOptional = demandeTirageRepository.findById(id);

		if (!demandetirageOptional.isPresent())
			return ResponseEntity.notFound().build();

		demandeTirage.setId(id);
		
		demandeTirageRepository.save(demandeTirage);
		 
		return ResponseEntity.noContent().build();
	    }
	  
	    @PostMapping
	    public void post(@Valid @RequestBody DemandeTirage demandeTirage) {
	    	demandeTirageRepository.save(demandeTirage);

	    }
	    
	    @DeleteMapping("/{id}")
	    public void delete(@PathVariable Long id) {
	    	demandeTirageRepository.deleteById(id);
	    }
	    
@GetMapping("/history")
@ResponseBody
public List gethistory(){
	List revisions = AuditReaderFactory.get(entityManager)
           .createQuery()
           .forRevisionsOfEntity(DemandeTirage.class, false, true)
           //.addProjection(AuditEntity.id())
           .addProjection( AuditEntity.revisionProperty("timestamp"))
           .addProjection(AuditEntity.revisionProperty("modifiedBy"))
           .addProjection(AuditEntity.revisionType())
           .getResultList();
	
	return revisions;
}
	

}

