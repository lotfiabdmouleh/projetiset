package tn.iset.model.tirage;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import tn.iset.model.Auditable;

@Entity
public class Intervention extends Auditable<String>{

	@Id
	@GeneratedValue
	private Long id;
	
	private String Nom_Societe;
	
	 @JsonIgnoreProperties("interventions")
	    @ManyToOne
	    private Photocopieur photocopieur;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom_Societe() {
		return Nom_Societe;
	}

	public void setNom_Societe(String nom_Societe) {
		Nom_Societe = nom_Societe;
	}

	public Photocopieur getPhotocopieur() {
		return photocopieur;
	}

	public void setPhotocopieur(Photocopieur photocopieur) {
		this.photocopieur = photocopieur;
	}

	
	
	
}
