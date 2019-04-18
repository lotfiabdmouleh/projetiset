package tn.iset.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.iset.model.Persone;
import tn.iset.repository.PersonneRepository;

@RestController
public class HomeController {
	
private PersonneRepository personneRepository;
@Autowired
public HomeController(PersonneRepository personneRepository) {
	
	this.personneRepository = personneRepository;
} 
@GetMapping("/personnes")
List<Persone>getAll(){
	return personneRepository.findAll();
	
}
@GetMapping("/")
String gll(){
	return "aaaaaaaaaaaaaaa";
}

}
