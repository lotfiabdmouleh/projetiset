package tn.iset.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.iset.model.User;
import tn.iset.repository.UserRepository;

@RequestMapping("/User")
@RestController
public class UserController {
@Autowired
private UserRepository userRepo;
    @GetMapping()
    public List<User> getAll() {
       return userRepo.findAll();
    }

    public String  a() {
   	 return "rrrr";
    }
    @GetMapping("/secured/all")
    public String securedHello() {
        return "Secured Hello";
    }

    @GetMapping("/secured/alternate")
    public String alternate() {
        return "alternate";
    }
}
