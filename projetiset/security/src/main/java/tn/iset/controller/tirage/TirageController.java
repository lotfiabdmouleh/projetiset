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
import tn.iset.model.tirage.Papier;
import tn.iset.model.tirage.Photocopieur;
import tn.iset.model.tirage.Recharge;
import tn.iset.model.tirage.Tirage;
import tn.iset.reopsitory.tirage.AgentTirageRepository;
import tn.iset.reopsitory.tirage.DemandeTirageRepository;
import tn.iset.reopsitory.tirage.PapierRepository;
import tn.iset.reopsitory.tirage.PhotocopieurRepository;
import tn.iset.reopsitory.tirage.TirageRepository;



@CrossOrigin("*")

@RestController
@RequestMapping("/tirage")

public class TirageController  {

	@Autowired
	private TirageRepository tirageRepository ;

	@Autowired
	private PhotocopieurRepository photocopieurRepository ;

	@Autowired
	private DemandeTirageRepository demandeTirageRepository ;
	@Autowired
	private PapierRepository papierRepository ;

	@Autowired
	private AgentTirageRepository agentTirageRepository ;
	
	@Autowired
	private EntityManager entityManager;

	public TirageController (TirageRepository tirageRepository) {
		super();
		this.tirageRepository = tirageRepository;
	}
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')or hasRole('AGENT')")
	public List<Tirage> getAll() {
		
		return tirageRepository.findAll();
	}
   
	@GetMapping("/{id}")
	public Tirage get(@PathVariable Long id) {
		
		return tirageRepository.findById(id).get();
	}
	
	  @PutMapping("/{id}")
	    public ResponseEntity<Recharge> put(@PathVariable Long id, @RequestBody Tirage tirage) {
	       Optional<Tirage> TirageOptional = tirageRepository.findById(id);

		if (!TirageOptional.isPresent())
			return ResponseEntity.notFound().build();

		tirage.setId(id);
		
		tirageRepository.save(tirage);
		 
		return ResponseEntity.noContent().build();
	    }
	  
	    @PostMapping("/{agent}/{papier}/{ph}")
	    public void post(@Valid @PathVariable Long agent,@PathVariable Long papier,@PathVariable Long ph,
	    		@RequestBody DemandeTirage demandeTirage) {
	    	Tirage tirage =new Tirage();
	    	Photocopieur p=photocopieurRepository.findById(ph).get();
	    	Papier pap=papierRepository.findById(papier).get();
	    	pap.setNb_feuille(pap.getNb_feuille()-demandeTirage.getNb_copie());
	    	tirage.setPapiers(pap);
	    	tirage.setPhotocopieur(p);
	    	demandeTirage.setEtat("Document imprimé");  
	    	
	    	tirage.getDemandeTirages().add(demandeTirage);
	    
	    	demandeTirage.setId(demandeTirage.getId());
	    	demandeTirageRepository.save(demandeTirage);
	    	AgentTirage agentTirage=agentTirageRepository.findById(agent).get();
	    	agentTirage.addTirage(tirage);
	    	tirageRepository.save(tirage);

	    }
	    @PostMapping("/file/{id}")
	    public void post(@Valid @PathVariable Long id,	@RequestBody String file) {
	    	DemandeTirage demandeTirage=demandeTirageRepository.findById(id).get();
	    	demandeTirage.setEtat(file);  
	    	
	    	demandeTirage.setId(demandeTirage.getId());
	    	demandeTirageRepository.save(demandeTirage);
	    

	    }
	   
	   
	    
	    
	    
	    @GetMapping("/demande")
	    @ResponseBody
	    public List getdemande() {
	    	return tirageRepository.getdem("en attente");
	    }
	    
	    
	    
	    
	    @DeleteMapping("/{id}")
	    public void delete(@PathVariable Long id) {
	    	tirageRepository.deleteById(id);
	    }
	    
	    
	    
@GetMapping("/history")
@ResponseBody
public List gethistory(){
	List revisions = AuditReaderFactory.get(entityManager)
           .createQuery()
           .forRevisionsOfEntity(Tirage.class, false, true)
           //.addProjection(AuditEntity.id())
           .addProjection( AuditEntity.revisionProperty("timestamp"))
           .addProjection(AuditEntity.revisionProperty("modifiedBy"))
           .addProjection(AuditEntity.revisionType())
           .getResultList();
	
	return revisions;
}}