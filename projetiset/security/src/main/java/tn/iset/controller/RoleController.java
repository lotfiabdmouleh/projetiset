package tn.iset.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RestController;

import tn.iset.model.Agent;
import tn.iset.model.Role;
import tn.iset.repository.AgentRepository;
import tn.iset.repository.RoleRepository;

@CrossOrigin("*")

@RestController
@RequestMapping("/role")
public class RoleController {
	@Autowired
	private RoleRepository roleRepository;

	public RoleController(RoleRepository roleRepository) {
		super();
		this.roleRepository = roleRepository;
	}
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')or hasRole('PM')")
	public List<Role> getAll() {
		return roleRepository.findAll();
	}
    
	@GetMapping("/{id}")
	public Role get(@PathVariable Long id) {
		return roleRepository.findById(id).get();
	}
	  @PutMapping("/{id}")
	    public ResponseEntity<Role> put(@PathVariable Long id, @RequestBody Role role ) {
	       Optional<Role> roleOptional = roleRepository.findById(id);

		if (!roleOptional.isPresent())
			return ResponseEntity.notFound().build();

		role.setId(id);
		
		roleRepository.save(role);

		return ResponseEntity.noContent().build();
	    }
	    
	    @PostMapping
	    public void post(@RequestBody Role role) {
	    	roleRepository.save(role);

	    }
	    
	    @DeleteMapping("/{id}")
	    public void delete(@PathVariable Long id) {
	    	roleRepository.deleteById(id);
	    }

	


}
