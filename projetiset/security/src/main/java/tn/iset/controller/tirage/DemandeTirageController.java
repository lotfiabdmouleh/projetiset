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
import tn.iset.model.tirage.Enseignement;
import tn.iset.reopsitory.tirage.DemandeTirageRepository;
import tn.iset.reopsitory.tirage.EnseignementRepository;


@CrossOrigin("*")

@RestController
@RequestMapping("/demandeTirage")

public class DemandeTirageController  {

	@Autowired
	private DemandeTirageRepository demandeTirageRepository;
	@Autowired
	private EntityManager entityManager;

	@Autowired
	private EnseignementRepository enseignementRepository;
	public DemandeTirageController (DemandeTirageRepository demandeTirageRepository) {
		super();
		this.demandeTirageRepository = demandeTirageRepository;
	}
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')or hasRole('PM')or hasRole('AGENT')")
	public List<DemandeTirage> getAll() {
		
		return demandeTirageRepository.findAll();
	}
   
	@GetMapping("/{id}")
	public DemandeTirage get(@PathVariable Long id) {
		
		return demandeTirageRepository.findById(id).get();
	}
	
	    @PostMapping("/{file}")
	    public void post(@Valid @PathVariable String file,@RequestBody Enseignement enseignement) {
	    	DemandeTirage demandeTirage=new DemandeTirage();
	    	demandeTirage.setNb_copie(enseignement.getGroupe().getNb_etd());
	    	demandeTirage.setFile(file);
	    	demandeTirage.setEnseignement(enseignement);
	    	demandeTirage.setEtat("Document en attente");
	    	demandeTirageRepository.save(demandeTirage);
	    	enseignement.addDemandeTirages(demandeTirage);
	    	enseignement.setId(enseignement.getId());
	    	enseignementRepository.save(enseignement);

	    }
	    
	    
	    @GetMapping("/user/{user}")
	    @ResponseBody
	    public List getdemande(@PathVariable String user) {
	    	return demandeTirageRepository.getEns(user);
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

