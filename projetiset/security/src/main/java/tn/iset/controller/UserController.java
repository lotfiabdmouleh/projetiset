package tn.iset.controller;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import tn.iset.model.Role;
import tn.iset.model.User;
import tn.iset.repository.RoleRepository;
import tn.iset.repository.UserRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/users")
public class UserController {
@Autowired
private UserRepository userRepo;
@Autowired
private RoleRepository rolerep;

@Autowired
PasswordEncoder encoder;
    @GetMapping()
	@PreAuthorize("hasRole('ADMIN')or hasRole('PM')")
    public List<User> getAll() {
       return userRepo.findAll();
    }
    @GetMapping("/{id}")
	public User get(@PathVariable Long id) {
		
		return userRepo.findById(id).get();
	}
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
		public void AffecterRole(@PathVariable Long id, @RequestBody List<User> users){
		
			Role role=rolerep.findById(id).get();
			for(int i=0;i<users.size();i++) {
				System.out.println(users.get(i).getRoles().size());
				users.get(i).getRoles().clear();
				users.get(i).getRoles().add(role);
				users.get(i).setId(users.get(i).getId());
				userRepo.save(users.get(i));
			}
				
	}
	  @PutMapping("put/{id}")
	    public ResponseEntity<User> put(@PathVariable Long id, @RequestBody User user ) {
	       Optional<User> agentOptional = userRepo.findById(id);

		if (!agentOptional.isPresent())
			return ResponseEntity.notFound().build();

		user.setId(id);
		
		userRepo.save(user);
		 
		return ResponseEntity.noContent().build();
	    }
	  
	    @PostMapping
	    public void post(@Valid @RequestBody User user) {
	    	userRepo.save(user);

	    }
	    
	    @DeleteMapping("/{id}")
	    public void delete(@PathVariable Long id) {
	    	userRepo.deleteById(id);
	    }
	    @GetMapping("/byname/{name}")
	    public Optional<User> getUserByName(@PathVariable String name){
	    	return userRepo.findByUsername(name);
	    }
	    @PutMapping("password/{id}/{oldpass}")
	    public void changePass(@PathVariable Long id,@PathVariable String oldpass,@RequestBody String passowrd){
	    	User u=userRepo.findById(id).get();
	    
	    	if(encoder.matches(oldpass, u.getPassword())){
	    		u.setId(id);
	    		u.setPassword(encoder.encode(passowrd));
	    		userRepo.save(u);
	    	}
	    	
	    }
	   
}
