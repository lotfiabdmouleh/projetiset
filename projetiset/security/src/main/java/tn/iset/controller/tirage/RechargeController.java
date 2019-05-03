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

import tn.iset.model.tirage.Recharge;
import tn.iset.reopsitory.tirage.RechargeRepository;



@CrossOrigin("*")

@RestController
@RequestMapping("/recharge")

public class RechargeController  {

	@Autowired
	private RechargeRepository rechargeRepository ;
	@Autowired
	private EntityManager entityManager;

	public RechargeController ( RechargeRepository rechargeRepository) {
		super();
		this.rechargeRepository = rechargeRepository;
	}
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public List<Recharge> getAll() {
		
		return rechargeRepository.findAll();
	}
   
	@GetMapping("/{id}")
	public Recharge get(@PathVariable Long id) {
		
		return rechargeRepository.findById(id).get();
	}
	
	  @PutMapping("/{id}")
	    public ResponseEntity<Recharge> put(@PathVariable Long id, @RequestBody Recharge recharge) {
	       Optional<Recharge> RechargeOptional = rechargeRepository.findById(id);

		if (!RechargeOptional.isPresent())
			return ResponseEntity.notFound().build();

		recharge.setId(id);
		
		rechargeRepository.save(recharge);
		 
		return ResponseEntity.noContent().build();
	    }
	  
	    @PostMapping
	    public void post(@Valid @RequestBody Recharge recharge) {
	    	rechargeRepository.save(recharge);

	    }
	    
	    @DeleteMapping("/{id}")
	    public void delete(@PathVariable Long id) {
	    	rechargeRepository.deleteById(id);
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
		}}