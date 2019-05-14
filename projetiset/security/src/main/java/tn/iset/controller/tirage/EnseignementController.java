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

import tn.iset.model.tirage.Departement;
import tn.iset.model.tirage.Enseignant;
import tn.iset.model.tirage.Enseignement;
import tn.iset.model.tirage.Groupe;
import tn.iset.model.tirage.Matiere;
import tn.iset.reopsitory.tirage.DepartementRepository;
import tn.iset.reopsitory.tirage.EnseignantRepository;
import tn.iset.reopsitory.tirage.EnseignementRepository;
import tn.iset.reopsitory.tirage.GroupeRepository;
import tn.iset.reopsitory.tirage.MatiereRepository;



@CrossOrigin("*")

@RestController
@RequestMapping("/enseignemant")

public class EnseignementController  {

	@Autowired
	private EnseignementRepository enseignementRepository ;
	@Autowired
	private EntityManager entityManager;

	@Autowired
	private DepartementRepository departementRepository;
	@Autowired
	private EnseignantRepository enseignantRepository;
	@Autowired
	private GroupeRepository groupeRepository;
	@Autowired
	private MatiereRepository matiereRepository;
	public EnseignementController ( EnseignementRepository enseignementRepository) {
		super();
		this.enseignementRepository = enseignementRepository;
	}
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public List<Enseignement> getAll() {
		
		return enseignementRepository.findAll();
	}
   
	@GetMapping("/{id}")
	public Enseignement get(@PathVariable Long id) {
		
		return enseignementRepository.findById(id).get();
	}
	
	  @PutMapping("/{id}/{dep}/{ens}/{grp}/{mat}")
	    public ResponseEntity<Enseignement> put(@PathVariable Long id,  @PathVariable Long dep, @PathVariable Long ens,@PathVariable Long grp,@PathVariable Long mat,@RequestBody Enseignement e) {
	       Optional<Enseignement> EnseignementOptional = enseignementRepository.findById(id);

		if (!EnseignementOptional.isPresent())
			return ResponseEntity.notFound().build();
		Departement d = departementRepository.findById(dep).get();
    	Enseignant en = enseignantRepository.findById(ens).get();
    	Matiere m=matiereRepository.findById(mat).get();
    	Groupe g =groupeRepository.findById(grp).get();
    	e.setDepartement(d);
    	e.setEnseignant(en);
    	e.setGroupe(g);
    	e.setMatiere(m);
		e.setId(id);
		
		enseignementRepository.save(e);
		 
		return ResponseEntity.noContent().build();
	    }
	  
	    @PostMapping("/{dep}/{ens}/{grp}/{mat}")
	    public void post(@Valid @PathVariable Long dep, @PathVariable Long ens,@PathVariable Long grp,@PathVariable Long mat,@RequestBody Enseignement enseignement) {
	    	Enseignement e =new Enseignement();
	    	Departement d = departementRepository.findById(dep).get();
	    	Enseignant en = enseignantRepository.findById(ens).get();
	    	Matiere m=matiereRepository.findById(mat).get();
	    	Groupe g =groupeRepository.findById(grp).get();
	    	e.setDepartement(d);
	    	e.setEnseignant(en);
	    	e.setGroupe(g);
	    	e.setMatiere(m);
	    	enseignementRepository.save(e);

	    }
	    
	    @DeleteMapping("/{id}")
	    public void delete(@PathVariable Long id) {
	    	enseignementRepository.deleteById(id);
	    }
	    
@GetMapping("/history")
@ResponseBody
public List gethistory(){
	List revisions = AuditReaderFactory.get(entityManager)
           .createQuery()
           .forRevisionsOfEntity(Enseignement.class, false, true)
           //.addProjection(AuditEntity.id())
           .addProjection( AuditEntity.revisionProperty("timestamp"))
           .addProjection(AuditEntity.revisionProperty("modifiedBy"))
           .addProjection(AuditEntity.revisionType())
           .getResultList();
	
	return revisions;
}}
	