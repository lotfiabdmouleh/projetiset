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
import tn.iset.model.tirage.Departement;
import tn.iset.reopsitory.tirage.DemandeTirageRepository;
import tn.iset.reopsitory.tirage.DepartementRepository;


@CrossOrigin("*")

@RestController
@RequestMapping("/departement")

public class DepartementController  {

	@Autowired
	private DepartementRepository departementRepository ;
	@Autowired
	private EntityManager entityManager;

	public DepartementController ( DepartementRepository departementRepository) {
		super();
		this.departementRepository = departementRepository;
	}
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public List<Departement> getAll() {
		
		return departementRepository.findAll();
	}
   
	@GetMapping("/{id}")
	public Departement get(@PathVariable Long id) {
		
		return departementRepository.findById(id).get();
	}
	
	  @PutMapping("/{id}")
	    public ResponseEntity<Departement> put(@PathVariable Long id, @RequestBody Departement dept ) {
	       Optional<Departement> deptOptional = departementRepository.findById(id);

		if (!deptOptional.isPresent())
			return ResponseEntity.notFound().build();

		dept.setId(id);
		
		departementRepository.save(dept);
		 
		return ResponseEntity.noContent().build();
	    }
	  
	    @PostMapping
	    public void post(@Valid @RequestBody Departement dept) {
	    	departementRepository.save(dept);

	    }
	    
	    @DeleteMapping("/{id}")
	    public void delete(@PathVariable Long id) {
	    	departementRepository.deleteById(id);
	    }
	    
@GetMapping("/history")
@ResponseBody
public List gethistory(){
	List revisions = AuditReaderFactory.get(entityManager)
           .createQuery()
           .forRevisionsOfEntity(Departement.class, false, true)
           //.addProjection(AuditEntity.id())
           .addProjection( AuditEntity.revisionProperty("timestamp"))
           .addProjection(AuditEntity.revisionProperty("modifiedBy"))
           .addProjection(AuditEntity.revisionType())
           .getResultList();
	
	return revisions;
}}
	
