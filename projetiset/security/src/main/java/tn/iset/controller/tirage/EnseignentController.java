package tn.iset.controller.tirage;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.validation.Valid;

import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
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

import tn.iset.message.request.SignUpForm;
import tn.iset.message.response.ResponseMessage;
import tn.iset.model.Role;
import tn.iset.model.RoleName;
import tn.iset.model.User;
import tn.iset.model.tirage.Enseignant;
import tn.iset.reopsitory.tirage.EnseignantRepository;
import tn.iset.repository.RoleRepository;
import tn.iset.repository.UserRepository;


@CrossOrigin("*")

@RestController
@RequestMapping("/enseignant")

public class EnseignentController  {
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	private EnseignantRepository enseignantRepository ;
	@Autowired
	private EntityManager entityManager;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	public EnseignentController ( EnseignantRepository enseignantRepository) {
		super();
		this.enseignantRepository = enseignantRepository;
	}
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public List<Enseignant> getAll() {
		
		return enseignantRepository.findAll();
	}
   
	@GetMapping("/{id}")
	public Enseignant get(@PathVariable Long id) {
		
		return enseignantRepository.findById(id).get();
	}
	
	  @PutMapping("/{id}")
	    public ResponseEntity<Enseignant> put(@PathVariable Long id, @RequestBody Enseignant enseignant ) {
	       Optional<Enseignant> EnseignantOptional = enseignantRepository.findById(id);

		if (!EnseignantOptional.isPresent())
			return ResponseEntity.notFound().build();

		enseignant.setId(id);
		
		enseignantRepository.save(enseignant);
		 
		return ResponseEntity.noContent().build();
	    }
	  
	    @PostMapping
	    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
			if (userRepository.existsByUsername(signUpRequest.getUsername())) {
				return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
						HttpStatus.BAD_REQUEST);
			}

			if (userRepository.existsByEmail(signUpRequest.getEmail())) {
				return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
						HttpStatus.BAD_REQUEST);
			}

			// Creating user's account
			User user = new Enseignant();
			user.setEmail(signUpRequest.getEmail());
			user.setName(signUpRequest.getName());
			user.setPassword(encoder.encode(signUpRequest.getPassword()));
			user.setUsername(signUpRequest.getUsername());
			user.setTel(signUpRequest.getTel());
			
			Set<String> strRoles = signUpRequest.getRole();
			Set<Role> roles = new HashSet<>();

			 
					Role adminRole = roleRepository.findByName(RoleName.ROLE_PM)
							.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
					roles.add(adminRole);


			user.setRoles(roles);
			userRepository.save((Enseignant) user);

			return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
		}

	    
	    @DeleteMapping("/{id}")
	    public void delete(@PathVariable Long id) {
	    	enseignantRepository.deleteById(id);
	    }
	    
@GetMapping("/history")
@ResponseBody
public List gethistory(){
	List revisions = AuditReaderFactory.get(entityManager)
           .createQuery()
           .forRevisionsOfEntity(Enseignant.class, false, true)
           //.addProjection(AuditEntity.id())
           .addProjection( AuditEntity.revisionProperty("timestamp"))
           .addProjection(AuditEntity.revisionProperty("modifiedBy"))
           .addProjection(AuditEntity.revisionType())
           .getResultList();
	
	return revisions;
}}
	