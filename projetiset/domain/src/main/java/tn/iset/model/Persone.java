package tn.iset.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Persone {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
	
private String prenom;
private String nom;

public Persone() {
	super();
}
public Persone(String prenom, String nom) {
	super();
	this.prenom = prenom;
	this.nom = nom;
}
public String getPrenom() {
	return prenom;
}
public void setPrenom(String prenom) {
	this.prenom = prenom;
}
public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}

}
